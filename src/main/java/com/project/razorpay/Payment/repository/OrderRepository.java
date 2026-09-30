package com.project.razorpay.Payment.repository;

import com.project.razorpay.Payment.entity.OrderRecord;

import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface    OrderRepository extends JpaRepository<OrderRecord, UUID>{



    boolean existsByMerchantIdAndReceipt(UUID merchantId, @Size(max=100) String receipt);
}
