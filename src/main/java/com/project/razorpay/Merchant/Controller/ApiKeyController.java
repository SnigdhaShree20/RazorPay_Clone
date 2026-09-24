package com.project.razorpay.Merchant.Controller;


import com.project.razorpay.Merchant.dto.request.CreateApiKeyRequest;
import com.project.razorpay.Merchant.dto.response.ApiKeyResponse;
import com.project.razorpay.Merchant.services.ApiKeyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/merchants/{merchantId}/api-keys")
@RequiredArgsConstructor
public class ApiKeyController {

    public final ApiKeyService apiKeyService;

    @PostMapping
    public ResponseEntity<ApiKeyResponse> create(@PathVariable UUID merchantId,
                                                 @Valid @RequestBody CreateApiKeyRequest request)
    {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(apiKeyService.create(merchnatId,request));
    }

}
