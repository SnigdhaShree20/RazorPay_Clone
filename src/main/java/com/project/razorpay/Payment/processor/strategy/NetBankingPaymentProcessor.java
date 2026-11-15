package com.project.razorpay.Payment.processor.strategy;

import com.project.razorpay.Payment.processor.PaymentProcessor;
import com.project.razorpay.Payment.processor.dto.PaymentProcessorRequest;
import com.project.razorpay.Payment.processor.dto.PaymentProcessorResponse;

public class NetBankingPaymentProcessor implements PaymentProcessor {

    @Override
    public PaymentProcessorResponse charge(PaymentProcessorRequest request){
        return null;
    }
}
