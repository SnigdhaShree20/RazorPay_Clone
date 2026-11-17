package com.project.razorpay.Payment.gateway.adapter;

import com.project.razorpay.Payment.gateway.PaymentAdapter;
import com.project.razorpay.Payment.gateway.dto.PaymentRequest;
import com.project.razorpay.Payment.gateway.dto.PaymentResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class CardPaymentAdapter implements PaymentAdapter {
    @Override
    public PaymentResult initiate(PaymentRequest request)
    {
        return null;
    }
}
