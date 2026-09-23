package com.project.razorpay.Merchant.dto.response;

import com.project.razorpay.Common.enums.BusinessType;
import com.project.razorpay.Common.enums.MerchantStatus;

import java.util.UUID;

public record MerchantResponse(
        UUID id,
        String name,
        String email,
        String businessName,
        BusinessType businessType,
        MerchantStatus merchantStatus
) {
}
