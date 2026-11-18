package com.project.razorpay.Payment.processor.strategy;

import com.project.razorpay.Common.util.RandomizerUtil;
import com.project.razorpay.Payment.processor.PaymentProcessor;
import com.project.razorpay.Payment.processor.dto.PaymentProcessorRequest;
import com.project.razorpay.Payment.processor.dto.PaymentProcessorResponse;

public class UpiPaymentProcessor implements PaymentProcessor {

    @Override
    public PaymentProcessorResponse charge(PaymentProcessorRequest request){
        final String VPA_CODE_FAIL="fail@okaxis";

        String bankCode=request.methodDetails() != null ?
                request.methodDetails().get("vpa").toString():null;
        // SIMULATION
        if(VPA_CODE_FAIL.equals(bankCode)){
            return new PaymentProcessorResponse.Failure("UPI_REJECTED",
                    "UPI rejected the transaction registration");}

        String processorRef="UPI_PROCESSOR"+ RandomizerUtil.randomnBase64(16);
        String bankRef="BANK_REF"+ RandomizerUtil.randomnBase64(16);

        return new PaymentProcessorResponse.Success(processorRef,bankRef);



    }
}
