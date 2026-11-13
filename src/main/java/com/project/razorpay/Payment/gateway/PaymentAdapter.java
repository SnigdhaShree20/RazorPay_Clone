package com.project.razorpay.Payment.gateway;

import com.project.razorpay.Payment.gateway.dto.PaymentRequest;

public interface PaymentAdapter {


    public void initiate(PaymentRequest request);

}
