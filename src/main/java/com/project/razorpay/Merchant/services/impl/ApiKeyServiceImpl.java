package com.project.razorpay.Merchant.services.impl;

import com.project.razorpay.Common.exceptions.ResourceNotFoundException;
import com.project.razorpay.Merchant.dto.request.CreateApiKeyRequest;
import com.project.razorpay.Merchant.dto.response.ApiKeyResponse;
import com.project.razorpay.Merchant.entity.ApiKey;
import com.project.razorpay.Merchant.entity.Merchant;
import com.project.razorpay.Merchant.repository.ApiKeyRepository;
import com.project.razorpay.Merchant.repository.MerchantRepository;
import com.project.razorpay.Merchant.services.ApiKeyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApiKeyServiceImpl implements ApiKeyService {

    private final MerchantRepository merchantRepository;
    private final ApiKeyRepository apiKeyRepository;

    @Override
    public ApiKeyResponse create(UUID merchantId, CreateApiKeyRequest request)
    {
        Merchant merchant =merchantRepository.findById(merchantId)
                .orElseThrow(()-> new ResourceNotFoundException("merchant",merchantId));

        String keyId="rzp_"+request.environment().name().toUpperCase()+"big_random_string";
        String rawSecret="big_randomn_secret";//TODO:replace with cryptographic secret

        ApiKey apiKey= ApiKey.builder()
                .merchant(merchant)
                .keyId(keyId)
                .keySecretHash(rawSecret)
                .environment(request.environment())
                .build();


        apiKey=apiKeyRepository.save(apiKey);
        return new ApiKeyResponse(apiKey.getId(),keyId,rawSecret,request.environment());
    }

}
