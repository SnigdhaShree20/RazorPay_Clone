package com.project.razorpay.Payment.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.razorpay.Common.entity.Money;
import com.project.razorpay.Common.enums.PaymentMethod;
import com.project.razorpay.Common.enums.PaymentStatus;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record PaymentResponse (UUID id,
                               UUID orderId,
                               UUID merchantId,
                               Money amount,
                               PaymentStatus status,
                               PaymentMethod method,
                               Map<String ,Object> methodDetails,
                               String errorCode,
                               String errorDescription,
                               Long refundedAmountPaise,
                               LocalDateTime capturedAt,
                               LocalDateTime createdAt)
{

}



