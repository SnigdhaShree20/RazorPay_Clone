package com.project.razorpay.Merchant.dto.request;

import com.project.razorpay.Common.enums.Environment;

public record CreateApiKeyRequest(
        Environment environment
) {
}
