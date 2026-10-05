package com.project.razorpay.Payment.mapper;

import com.project.razorpay.Payment.dto.response.OrderResponse;
import com.project.razorpay.Payment.entity.OrderRecord;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel= MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {
    OrderResponse toResponse(OrderRecord orderRecord);
}
