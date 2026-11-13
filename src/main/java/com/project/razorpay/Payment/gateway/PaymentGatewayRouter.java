package com.project.razorpay.Payment.gateway;

import com.project.razorpay.Common.enums.PaymentMethod;
import com.project.razorpay.Payment.gateway.dto.PaymentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class PaymentGatewayRouter {
    private final Map<PaymentMethod,PaymentAdapter> paymentAdapters;

   public  void initiate(PaymentRequest request){
        //handles the switch cases or if else here

       PaymentAdapter adapter= paymentAdapters.get(request.method());

       if(adapter == null ){
           throw new IllegalArgumentException("No payment adapter registered for method:"+request.method());
       }
       adapter.initiate(request);

    }
}
