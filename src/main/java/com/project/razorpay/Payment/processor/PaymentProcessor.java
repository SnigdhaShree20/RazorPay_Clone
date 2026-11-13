package com.project.razorpay.Payment.processor;

import com.project.razorpay.Payment.processor.dto.PaymentProcessorRequest;
import com.project.razorpay.Payment.processor.dto.PaymentProcessorResponse;

public interface PaymentProcessor {

    PaymentProcessorResponse charge(PaymentProcessorRequest request);
}
