package com.project.razorpay.Payment.controller;

import com.project.razorpay.Payment.dto.request.PaymentInitRequest;
import com.project.razorpay.Payment.dto.response.PaymentResponse;
import com.project.razorpay.Payment.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/payments")
public class PaymentController {
    private final PaymentService paymentService;
    UUID merchantId= UUID.fromString("31f1faa3-2fdd-4a86-b530-9fe99bfe243f");//this will come from the auth token(merchant context)

    @PostMapping
    public ResponseEntity<PaymentResponse> initiate( @Valid @RequestBody PaymentInitRequest request)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentService.initiate(merchantId, request));
    }

    @PostMapping("/{paymentId}/capture")
    public ResponseEntity<PaymentResponse> capture(@PathVariable UUID paymentId)
    {
        return ResponseEntity.ok(paymentService.capture(merchantId,paymentId));
    }
}
