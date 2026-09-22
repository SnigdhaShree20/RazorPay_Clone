package com.project.razorpay.Payment.entity;

import com.project.razorpay.Common.entity.Money;
import com.project.razorpay.Common.enums.OrderStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name="order_record")
public class OrderRecord {
    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    private UUID uuid;

    //no FK cross service boundary
    private UUID merchantId;

    @Embedded
    private Money money;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false,length=15)
    private OrderStatus orderStatus;

    @Column(nullable=false)
    private Integer attempts=0;
    @JdbcTypeCode((SqlTypes.JSON))
    @Column(columnDefinition = "jsonb")
    private Map<String,Object> notes;

    @Column(nullable=false)
    private LocalDateTime createdAt;

}
