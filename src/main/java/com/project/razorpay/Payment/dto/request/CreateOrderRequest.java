package com.project.razorpay.Payment.dto.request;

import com.project.razorpay.Common.entity.Money;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.Map;

public record CreateOrderRequest(
        @NotNull(message="amount is required")
        Money amount,
        @Size(max=100)
        String  receipt,//order id known to merchant only unknown to Razorpay
        Map<String,Object> notes,
        LocalDateTime expiresAt



) {
}
