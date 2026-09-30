package com.project.razorpay.Payment.service.impl;

import com.project.razorpay.Common.enums.OrderStatus;
import com.project.razorpay.Common.exceptions.DuplicateResourceException;
import com.project.razorpay.Payment.dto.request.CreateOrderRequest;
import com.project.razorpay.Payment.dto.response.OrderResponse;
import com.project.razorpay.Payment.entity.OrderRecord;
import com.project.razorpay.Payment.repository.OrderRepository;
import com.project.razorpay.Payment.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Value("${payment.order.default-order-expiry-minutes:30}")
    private int defaultOrderExpiryMinutes;

    @Override
    public OrderResponse create(UUID merchantId, CreateOrderRequest request) {

        // 1. Check if receipt already exists
        if (request.receipt() != null
                && orderRepository.existsByMerchantIdAndReceipt(
                merchantId,
                request.receipt())) {

            throw new DuplicateResourceException(
                    "ORDER_RECEIPT_DUPLICATE",
                    "Order with receipt already exists: " + request.receipt()
            );
        }

        // 2. Create OrderRecord
        OrderRecord order = OrderRecord.builder()
                .receipt(request.receipt())
                .amount(request.amount())
                .notes(request.notes())
                .merchantId(merchantId)
                .orderStatus(OrderStatus.CREATED)
                .attempts(0)
                .createdAt(LocalDateTime.now())
                .expiresAt(
                        request.expiresAt() != null
                                ? request.expiresAt()
                                : LocalDateTime.now()
                                .plusMinutes(defaultOrderExpiryMinutes)
                )
                .build();

        // 3. Save order in database
        order = orderRepository.save(order);

        // TODO: publish Kafka event as order is created

        // 4. Return response
        return new OrderResponse(
                order.getId(),
                order.getMerchantId(),
                order.getReceipt(),
                order.getAmount(),
                order.getOrderStatus(),
                order.getAttempts(),
                order.getNotes(),
                order.getCreatedAt(),
                order.getExpiresAt()
        );
    }
}