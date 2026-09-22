package com.project.razorpay.Payment.entity;

import com.project.razorpay.Common.entity.Money;
import com.project.razorpay.Common.enums.PaymentMethod;
import com.project.razorpay.Common.enums.PaymentStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name="payment")
public class Payment {
    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY,optional=false)
    @JoinColumn(name="order_id",nullable=false)
    private OrderRecord order;

    @Column(nullable=false)
    private UUID merchantId;

    @Embedded
    private Money amount;
    @Column(nullable=false,length=100)
    private String idempotenceKey;
    @Enumerated(EnumType.STRING)
    @Column(nullable=false,length=30)
    private PaymentStatus status;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name="method",columnDefinition = "jsonb")
    private PaymentMethod method;
    @Column(length=100)
    private Map<String,Object> methodDetails;
    @Column(length=100)
    private String bankReference;
    @Column(length=255)
    private String failureReason;


    private LocalDateTime authorizedAt;
    private LocalDateTime capturedAt;
    private LocalDateTime failedAt;
    private LocalDateTime refundedAt;

}
