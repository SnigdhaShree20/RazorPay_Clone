package com.project.razorpay.Payment.config;

import com.project.razorpay.Common.enums.PaymentMethod;
import com.project.razorpay.Payment.gateway.PaymentAdapter;
import com.project.razorpay.Payment.gateway.adapter.CardPaymentAdapter;
import com.project.razorpay.Payment.gateway.adapter.NetBankingAdapter;
import com.project.razorpay.Payment.gateway.adapter.UpiPaymentAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class PaymentAdapterConfig {

    private final NetBankingAdapter netBankingAdapter;
    private final UpiPaymentAdapter upiPaymentAdapter;
    private final CardPaymentAdapter cardPaymentAdapter;

    @Bean
    public Map<PaymentMethod, PaymentAdapter> paymentAdapterMap()
    {
        return Map.of(
                PaymentMethod.CARD,cardPaymentAdapter,
                PaymentMethod.NETBANKING,netBankingAdapter,
                PaymentMethod.UPI,upiPaymentAdapter
        );
    }
}
