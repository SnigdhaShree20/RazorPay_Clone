package com.project.razorpay.Payment.gateway;

import com.project.razorpay.Payment.gateway.dto.PaymentRequest;
import com.project.razorpay.Payment.gateway.dto.PaymentResult;

import java.util.UUID;

public interface PaymentAdapter {


    public PaymentResult initiate(PaymentRequest request);

    PaymentResult capture(UUID paymentId);
}
