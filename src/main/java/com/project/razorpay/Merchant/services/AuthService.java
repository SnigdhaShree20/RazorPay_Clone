package com.project.razorpay.Merchant.services;

import com.project.razorpay.Merchant.dto.request.MerchantSignupRequest;
import com.project.razorpay.Merchant.dto.response.MerchantResponse;
import jakarta.validation.Valid;

public interface AuthService {
    MerchantResponse signup(@Valid MerchantSignupRequest request);
}
