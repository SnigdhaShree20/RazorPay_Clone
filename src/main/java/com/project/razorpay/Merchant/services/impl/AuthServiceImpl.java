package com.project.razorpay.Merchant.services.impl;

import com.project.razorpay.Common.enums.MerchantStatus;
import com.project.razorpay.Common.enums.UserRole;
import com.project.razorpay.Merchant.dto.request.MerchantSignupRequest;
import com.project.razorpay.Merchant.dto.response.MerchantResponse;
import com.project.razorpay.Merchant.entity.AppUser;
import com.project.razorpay.Merchant.entity.Merchant;
import com.project.razorpay.Merchant.repository.AppUserRepository;
import com.project.razorpay.Merchant.repository.MerchantRepository;
import com.project.razorpay.Merchant.services.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final AppUserRepository appUserRepository;
    private final MerchantRepository merchantRepository;

    @Override
    public MerchantResponse signup(MerchantSignupRequest request) {

        //merchant with same email cant be allowed
        if(merchantRepository.existsByEmail(request.email()))
        {
            throw new RuntimeException("Email already exists"+request.email());
        }

        Merchant merchant = Merchant.builder()
                .businessName(request.businessName())
                .businessType(request.businessType())
                .name(request.name())
                .email(request.email())
                .status(MerchantStatus.PENDING_KYC)

                .build();//this is in java heap memory

       merchant= merchantRepository.save(merchant);

       AppUser appUser=AppUser.builder()
               .email(request.email())
               .merchant(merchant)
               .passwordHash(request.password())//TODO: encrypt this using Bcrypt
               .role(UserRole.OWNER)
               .build();

       appUserRepository.save(appUser);

       return new MerchantResponse(merchant.getId(),merchant.getName(), merchant.getEmail(), merchant.getBusinessName(),
                merchant.getBusinessType(), merchant.getStatus());
    }//signup function end

}
