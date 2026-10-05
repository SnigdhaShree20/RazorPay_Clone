package com.project.razorpay.Merchant.mapper;


import com.project.razorpay.Merchant.dto.request.MerchantSignupRequest;
import com.project.razorpay.Merchant.dto.response.MerchantResponse;
import com.project.razorpay.Merchant.entity.Merchant;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel= MappingConstants.ComponentModel.SPRING)
public interface MerchantMapper {

    Merchant toEntityfromSignUpRequest(MerchantSignupRequest request);
    MerchantResponse toMerchantResponse(Merchant merchant);
}
