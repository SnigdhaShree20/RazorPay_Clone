package com.project.razorpay.Payment.processor;

import com.project.razorpay.Common.enums.PaymentMethod;
import com.project.razorpay.Payment.processor.dto.PaymentProcessorRequest;
import com.project.razorpay.Payment.processor.dto.PaymentProcessorResponse;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PaymentProcessorRouter {

    private Map<PaymentMethod,PaymentProcessor> paymentProcessors;

    public PaymentProcessorResponse charge(PaymentProcessorRequest request){
        PaymentProcessor processor= paymentProcessors.get(request.method());
        if(processor==null){
            throw new IllegalArgumentException("No Payment processor is registerd for method "+request.method());
        }

        return processor.charge(request);
    }


}
