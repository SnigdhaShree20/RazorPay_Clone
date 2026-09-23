package com.project.razorpay.Merchant.dto.request;


import com.project.razorpay.Common.enums.BusinessType;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record
MerchantSignupRequest (


    @NotNull(message = "name should be provided")
    @Size(max=50,message="Name should not be more than 50 characters")
    String name,

    @Email
    @NotNull(message="email is required")
    String email,

    @NotNull(message="password is required")
    @Size(min=8, message="size of the password should be minimum of 8 charcters")
    String password,

    @Size(max=50,message="business name should not be more than 50 characters")
    String businessName,


    BusinessType businessType
    )
{


}
