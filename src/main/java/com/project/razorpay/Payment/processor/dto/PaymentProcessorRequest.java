package com.project.razorpay.Payment.processor.dto;

import com.project.razorpay.Common.entity.Money;
import com.project.razorpay.Common.enums.PaymentMethod;

import java.util.Map;

public record    PaymentProcessorRequest (
        PaymentMethod method,
        Money amount,
        Map<String,Object> methodDetails
){

}




