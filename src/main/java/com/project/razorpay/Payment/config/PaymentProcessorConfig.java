package com.project.razorpay.Payment.config;


import com.project.razorpay.Common.enums.PaymentMethod;
import com.project.razorpay.Payment.processor.PaymentProcessor;
import com.project.razorpay.Payment.processor.strategy.CardPaymentProcessor;
import com.project.razorpay.Payment.processor.strategy.NetBankingPaymentProcessor;
import com.project.razorpay.Payment.processor.strategy.UpiPaymentProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class PaymentProcessorConfig {

    @Bean
    public Map<PaymentMethod, PaymentProcessor> paymentProcessorMap()
    {
        return Map.of(
                PaymentMethod.CARD,new CardPaymentProcessor(),
                PaymentMethod.NETBANKING,new NetBankingPaymentProcessor(),
                PaymentMethod.UPI,new UpiPaymentProcessor());
    }
}
