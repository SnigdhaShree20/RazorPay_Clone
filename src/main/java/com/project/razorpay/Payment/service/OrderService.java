package com.project.razorpay.Payment.service;

import com.project.razorpay.Payment.dto.request.CreateOrderRequest;
import com.project.razorpay.Payment.dto.response.OrderResponse;

import java.util.UUID;

public interface OrderService {
    OrderResponse create(UUID merchantId, CreateOrderRequest request);
}
