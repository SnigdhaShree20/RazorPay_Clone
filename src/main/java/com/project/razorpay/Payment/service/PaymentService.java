package com.project.razorpay.Payment.service;

import com.project.razorpay.Payment.dto.request.PaymentInitRequest;
import com.project.razorpay.Payment.dto.response.PaymentResponse;

import java.util.UUID;

public interface PaymentService {
    PaymentResponse initiate(UUID merchantID, PaymentInitRequest request);
}
