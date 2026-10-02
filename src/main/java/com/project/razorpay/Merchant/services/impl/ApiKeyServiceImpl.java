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
import jakarta.annotation.Nullable;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@org.springframework.transaction.annotation.Transactional(readOnly = true)
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

    @Transactional
    public void revoke(UUID merchantId, UUID keyId) {
        ApiKey key= apiKeyRepository.findById(keyId)
                .filter(k ->k.getMerchant().getId().equals(merchantId))
                .orElseThrow(()-> new ResourceNotFoundException("key",keyId));
        key.setEnabled(false);
    }

    @Override
    @Transactional
    public @Nullable ApiKeyCreateResponse rotate(UUID merchantId, UUID apikeyId) {
        //check the keyId and merchantId pair exists
        ApiKey apiKey = apiKeyRepository.findById(apikeyId)
                .filter(k -> k.getMerchant().getId().equals(merchantId))
                .orElseThrow(() -> new ResourceNotFoundException("key", apikeyId));

        if(!apiKey.isEnabled()) throw new RuntimeException("cannot rotate a disabled key");

        String newRawSecret=RandomizerUtil.randomnBase64(40);
        //set the prev secretKey as current secret key
        apiKey.setPrevKeySecretHash(apiKey.getKeySecretHash());
        //update the curr secret Key
        apiKey.setKeySecretHash(newRawSecret);//TODO:encode with BCRYPT password encoder.
        apiKey.setRotatedAt(LocalDateTime.now());
        apiKey.setGracePeriodExpiresAt(LocalDateTime.now().plusHours(24));
        apiKey=apiKeyRepository.save(apiKey);
        return new ApiKeyCreateResponse(apiKey.getId(), apiKey.getKeyId(), newRawSecret,apiKey.getEnvironment());
    }
}
