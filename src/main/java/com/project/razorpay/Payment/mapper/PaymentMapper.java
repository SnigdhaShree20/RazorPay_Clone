package com.project.razorpay.Payment.mapper;


import com.project.razorpay.Payment.dto.response.PaymentResponse;
import com.project.razorpay.Payment.entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel= MappingConstants.ComponentModel.SPRING)
public interface PaymentMapper {

    @Mapping(target="orderId", source="order.id")
    PaymentResponse toResponse(Payment payment);

    @Mapping(target="orderId", source="order.id")
    List<PaymentResponse> toResponseList(List<Payment> paymentList);
}
