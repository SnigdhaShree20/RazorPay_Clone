package com.project.razorpay.Payment.service.impl;

import com.project.razorpay.Common.enums.OrderStatus;
import com.project.razorpay.Common.enums.PaymentEvent;
import com.project.razorpay.Common.enums.PaymentStatus;
import com.project.razorpay.Common.exceptions.BusinessRuleViolationException;
import com.project.razorpay.Common.exceptions.ResourceNotFoundException;
import com.project.razorpay.Payment.dto.request.PaymentInitRequest;
import com.project.razorpay.Payment.dto.response.PaymentResponse;
import com.project.razorpay.Payment.entity.OrderRecord;
import com.project.razorpay.Payment.entity.Payment;
import com.project.razorpay.Payment.gateway.PaymentGatewayRouter;
import com.project.razorpay.Payment.gateway.dto.PaymentRequest;
import com.project.razorpay.Payment.gateway.dto.PaymentResult;
import com.project.razorpay.Payment.mapper.PaymentMapper;
import com.project.razorpay.Payment.repository.OrderRepository;
import com.project.razorpay.Payment.repository.PaymentRepository;
import com.project.razorpay.Payment.service.PaymentService;
import com.project.razorpay.Payment.statemachine.PaymentTransitionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;


@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;
    private final PaymentGatewayRouter paymentGatewayRouter;
    private final PaymentMapper paymentMapper;
    private final PaymentTransitionService paymentTransitionService;

    @Override
    @Transactional
    public PaymentResponse initiate(UUID merchantId, PaymentInitRequest request) {
        OrderRecord order= orderRepository.findByIdAndMerchantId(request.orderId(),merchantId).orElseThrow(() -> new ResourceNotFoundException("order",request.orderId()));

        if(order.getOrderStatus() != OrderStatus.CREATED && order.getOrderStatus() != OrderStatus.ATTEMPTED) {
            throw new BusinessRuleViolationException("ORDER_NOT_PAYABLE","Order cannot accept payment in status : "+order.getOrderStatus());
        }

        //initiate the payment
        order.setOrderStatus(OrderStatus.ATTEMPTED);
        order.setAttempts(order.getAttempts()+1);

        Payment payment =Payment.builder()
                .order(order)
                .merchantId(merchantId)
                .amount(order.getAmount())
                .status(PaymentStatus.CREATED)
                .method(request.method())
                .methodDetails(request.methodDetails())
                .build();

        payment=paymentRepository.save(payment);
        PaymentRequest paymentRequest = new PaymentRequest(
                payment.getId(),
                request.orderId(),
                merchantId,
                order.getAmount(),
                request.method(),
                request.methodDetails());

       PaymentResult result= paymentGatewayRouter.initiate(paymentRequest);

        switch (result) {
            case PaymentResult.Pending pending ->
                // We got registration reference
                    payment.setProcessorReference(pending.registrationReference());


            case PaymentResult.Failure failure -> {
                //payment.setStatus(PaymentStatus.FAILED);
                paymentTransitionService.apply(payment, PaymentEvent.AUTHORIZE_FAIL);
                payment.setErrorCode(failure.errorCode());
                payment.setErrorDescription(failure.errorDescription());

            }
            case PaymentResult.Success success -> {

            }
        }

        payment=paymentRepository.save(payment);
        orderRepository.save(order);

//TODO: send an outbox(kafka event)
        return paymentMapper.toResponse(payment);

    }

    @Override
    public PaymentResponse capture(UUID merchantId, UUID paymentId) {
        Payment payment=paymentRepository.findByIdAndMerchantId(paymentId,merchantId)
                .orElseThrow(() -> new ResourceNotFoundException("Payment",paymentId));

        //payment.setStatus(PaymentStatus.CAPTURING);
        paymentTransitionService.apply(payment,PaymentEvent.CAPTURE_REQUEST);
        PaymentResult paymentResult=paymentGatewayRouter.capture(payment.getMethod(),paymentId);
        if(paymentResult instanceof  PaymentResult.Success  success ) {

           // payment.setStatus(PaymentStatus.CAPTURED);
            paymentTransitionService.apply(payment, PaymentEvent.CAPTURE_SUCCESS);
            payment.setCapturedAt(LocalDateTime.now());
            log.info("Payment Captured ,paymentId: {}",paymentId);
        }
        else if (paymentResult instanceof  PaymentResult.Failure failure) {
           // payment.setStatus(PaymentStatus.AUTHORIZED);
            paymentTransitionService.apply(payment, PaymentEvent.CAPTURE_FAIL);
            payment.setErrorCode(failure.errorCode());
            payment.setErrorDescription(failure.errorDescription());
            log.warn("Payment capture failed,paymentId: {}",paymentId);
        }
        payment=paymentRepository.save(payment);
        //TODO: send an outbox(kafka event)

        return paymentMapper.toResponse(payment);
    }
}
