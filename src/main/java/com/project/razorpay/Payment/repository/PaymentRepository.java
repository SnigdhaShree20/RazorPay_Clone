package com.project.razorpay.Payment.repository;

import com.project.razorpay.Payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.ScopedValue;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {
    List<Payment> findByOrderId(UUID orderId);

    Optional<Payment> findByIdAndMerchantId(UUID paymentId, UUID merchantId);
}
