package com.project.razorpay.Merchant.repository;

import com.project.razorpay.Merchant.dto.response.ApiKeyResponse;
import com.project.razorpay.Merchant.entity.ApiKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ApiKeyRepository extends JpaRepository<ApiKey, UUID> {
    List<ApiKey> findByMerchant_Id(UUID merchantId);
}
