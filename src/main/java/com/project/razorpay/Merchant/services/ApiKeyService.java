package com.project.razorpay.Merchant.services;


import com.project.razorpay.Merchant.dto.request.CreateApiKeyRequest;
import com.project.razorpay.Merchant.dto.response.ApiKeyResponse;

import java.util.UUID;

public interface ApiKeyService {
    ApiKeyResponse create(UUID merchantId, CreateApiKeyRequest request);
}
