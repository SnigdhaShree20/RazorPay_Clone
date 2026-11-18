package com.project.razorpay.Payment.gateway.adapter;

import com.project.razorpay.Common.enums.PaymentMethod;
import com.project.razorpay.Payment.gateway.PaymentAdapter;
import com.project.razorpay.Payment.gateway.dto.PaymentRequest;
import com.project.razorpay.Payment.gateway.dto.PaymentResult;
import com.project.razorpay.Payment.processor.PaymentProcessorRouter;
import com.project.razorpay.Payment.processor.dto.PaymentProcessorRequest;
import com.project.razorpay.Payment.processor.dto.PaymentProcessorResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
@Slf4j
@RequiredArgsConstructor
public class NetBankingAdapter implements PaymentAdapter {
    private final PaymentProcessorRouter paymentProcessorRouter;

    @Override
    public PaymentResult initiate(PaymentRequest request)
    { log.info("initiate payment with NetBankingAdapter,paymentId: {}",request.paymentId());
        try{
        PaymentProcessorRequest paymentProcessorRequest=PaymentProcessorRequest.nonCard(
                request.paymentId(),
                PaymentMethod.NETBANKING,
                request.amount(),
                request.methodDetails()
        );

        PaymentProcessorResponse paymentProcessorResponse=
                paymentProcessorRouter.charge(paymentProcessorRequest);
        //response here calls the third party services and receives a reference

        //result
        return switch(paymentProcessorResponse){
            case PaymentProcessorResponse.Failure failure -> new PaymentResult.Failure(failure.errorCode(), failure.errorDescription());
            case PaymentProcessorResponse.Pending pending -> new PaymentResult.Pending(pending.processorReference());
            case PaymentProcessorResponse.Success success -> new  PaymentResult.Success(success.bankReference());//in case of refund
        };
        }
        catch(Exception e){
            log.warn("NetBanking Failed,paymentId: {}",request.paymentId());
            return new PaymentResult.Failure("NBK_FAILED",e.getMessage());
        }



    }

    @Override
    public PaymentResult capture(UUID paymentId) {
        return new PaymentResult.Success("NBK_REF");
    }
}
