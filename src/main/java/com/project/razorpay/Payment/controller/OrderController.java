package com.project.razorpay.Payment.controller;

import com.project.razorpay.Payment.dto.request.CreateOrderRequest;
import com.project.razorpay.Payment.dto.response.OrderResponse;
import com.project.razorpay.Payment.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    UUID merchantId = UUID.fromString(
            "31f1faa3-2fdd-4a86-b530-9fe99bfe243f"
    ); // TODO: replace with merchantContext

    @PostMapping
    public ResponseEntity<OrderResponse> create(
            @RequestBody @Valid CreateOrderRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(orderService.create(merchantId, request));
    }
}