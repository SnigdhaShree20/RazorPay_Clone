package com.project.razorpay.Merchant.dto.response;

import com.project.razorpay.Common.enums.Environment;

import java.util.UUID;

public record ApiKeyResponse(
        UUID id,
        String keyId,
        String keySecret,
       Environment environment
) {
}
