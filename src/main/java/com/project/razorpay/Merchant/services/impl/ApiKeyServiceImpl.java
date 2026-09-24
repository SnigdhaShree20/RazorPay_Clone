package com.project.razorpay.Merchant.services.impl;

import com.project.razorpay.Common.exceptions.ResourceNotFoundException;
import com.project.razorpay.Common.util.RandomizerUtil;
import com.project.razorpay.Merchant.dto.request.CreateApiKeyRequest;
import com.project.razorpay.Merchant.dto.response.ApiKeyCreateResponse;
import com.project.razorpay.Merchant.dto.response.ApiKeyResponse;
import com.project.razorpay.Merchant.entity.ApiKey;
import com.project.razorpay.Merchant.entity.Merchant;
import com.project.razorpay.Merchant.repository.ApiKeyRepository;
import com.project.razorpay.Merchant.repository.MerchantRepository;
import com.project.razorpay.Merchant.services.ApiKeyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApiKeyServiceImpl implements ApiKeyService {

    private final MerchantRepository merchantRepository;
    private final ApiKeyRepository apiKeyRepository;

    @Override
    public ApiKeyCreateResponse create(UUID merchantId, CreateApiKeyRequest request)
    {
        Merchant merchant =merchantRepository.findById(merchantId)
                .orElseThrow(()-> new ResourceNotFoundException("merchant",merchantId));

        String keyId="rzp_"+request.environment().name().toLowerCase()+ RandomizerUtil.randomnBase64(24);
        String rawSecret=RandomizerUtil.randomnBase64(40);

        ApiKey apiKey= ApiKey.builder()
                .merchant(merchant)
                .keyId(keyId)
                .keySecretHash(rawSecret)
                .environment(request.environment())
                .build();


        apiKey=apiKeyRepository.save(apiKey);
        return new ApiKeyCreateResponse(apiKey.getId(),keyId,rawSecret,request.environment());
    }

    @Override
    public List<ApiKeyResponse> listByMerchant(UUID merchantId) {

        return apiKeyRepository.findByMerchant_Id(merchantId)
                .stream()
                .map(apiKey -> new ApiKeyResponse(
                        apiKey.getId(),
                        apiKey.getKeyId(),
                        apiKey.getEnvironment(),
                        apiKey.isEnabled(),
                        apiKey.getLastUsedAt(),
                        null
                ))
                .toList();
    }
}
