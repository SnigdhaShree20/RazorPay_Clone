package com.project.razorpay.Payment.gateway;

import com.project.razorpay.Payment.gateway.dto.PaymentRequest;
import com.project.razorpay.Payment.gateway.dto.PaymentResult;

public interface PaymentAdapter {


    public PaymentResult initiate(PaymentRequest request);

}
