package com.project.razorpay.Payment.gateway.adapter;

import com.project.razorpay.Payment.gateway.PaymentAdapter;
import com.project.razorpay.Payment.gateway.dto.PaymentRequest;
import com.project.razorpay.Payment.gateway.dto.PaymentResult;

public class NetBankingAdapter implements PaymentAdapter {
    @Override
    public PaymentResult initiate(PaymentRequest request)
    {
        return null;
    }
}
