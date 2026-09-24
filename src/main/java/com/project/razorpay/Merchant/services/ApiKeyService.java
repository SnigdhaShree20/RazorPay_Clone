package com.project.razorpay.Merchant.services;


import com.project.razorpay.Merchant.dto.request.CreateApiKeyRequest;
import com.project.razorpay.Merchant.dto.response.ApiKeyCreateResponse;
import com.project.razorpay.Merchant.dto.response.ApiKeyResponse;

import java.util.List;
import java.util.UUID;

public interface ApiKeyService {
    ApiKeyCreateResponse create(UUID merchantId, CreateApiKeyRequest request);

    List<ApiKeyResponse> listByMerchant(UUID merchantId);
}
