package com.project.razorpay.Merchant.repository;

import com.project.razorpay.Merchant.entity.Merchant;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface MerchantRepository extends JpaRepository<Merchant, UUID> {

    boolean existsByEmail(@Email @NotNull(message="email is required") String email);
}
