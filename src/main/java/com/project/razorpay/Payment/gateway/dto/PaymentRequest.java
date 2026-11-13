package com.project.razorpay.Payment.gateway.dto;

import com.project.razorpay.Common.entity.Money;
import com.project.razorpay.Common.enums.PaymentMethod;

import java.util.Map;
import java.util.UUID;

public record PaymentRequest(
        UUID paymentId,
        UUID orderId,
        UUID merchantId,
        Money amount,
        PaymentMethod method,
        Map<String,Object> methodDetails
) {
}
