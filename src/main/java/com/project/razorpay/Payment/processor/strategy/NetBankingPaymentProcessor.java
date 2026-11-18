package com.project.razorpay.Payment.processor.strategy;

import com.project.razorpay.Common.util.RandomizerUtil;
import com.project.razorpay.Payment.processor.PaymentProcessor;
import com.project.razorpay.Payment.processor.dto.PaymentProcessorRequest;
import com.project.razorpay.Payment.processor.dto.PaymentProcessorResponse;

public class NetBankingPaymentProcessor implements PaymentProcessor {

    @Override
    public PaymentProcessorResponse charge(PaymentProcessorRequest request){
        final String BANK_CODE_FAIL="BANK_CODE_FAIL";

        String bankCode=request.methodDetails() != null ?
                request.methodDetails().get("BANK").toString():null;
        // SIMULATION
        if(BANK_CODE_FAIL.equals(bankCode)){
            return new PaymentProcessorResponse.Failure("BANK_REJECTED",
                    "Bank rejected the transaction registration");}

        String processorRef="NBK_PROCESSOR"+ RandomizerUtil.randomnBase64(16);
        String redirectRef="http://REDIRECT_BANK.com/"+processorRef;

        return new PaymentProcessorResponse.Success(processorRef,redirectRef);
    }
}
