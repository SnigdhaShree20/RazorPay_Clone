package com.project.razorpay.Payment.service.impl;

import com.project.razorpay.Payment.dto.request.PaymentInitRequest;
import com.project.razorpay.Payment.dto.response.PaymentResponse;
import com.project.razorpay.Payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentServiceImp implements PaymentService {
    @Override
    public PaymentResponse initiate(UUID merchantID, PaymentInitRequest request) {
        return null;
    }
}
