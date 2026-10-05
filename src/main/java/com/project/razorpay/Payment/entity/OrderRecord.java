package com.project.razorpay.Payment.entity;

import com.project.razorpay.Common.entity.BaseEntity;
import com.project.razorpay.Common.entity.Money;
import com.project.razorpay.Common.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name="order_record",indexes={@Index(name="idx_order_id_merchant_id",columnList="id,merchant_id")})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRecord extends BaseEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    private UUID id;

    //no FK cross service boundary
    private UUID merchantId;

    @Embedded
    private Money amount;

    @Column(length=100)
    private String receipt;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false,length=15)
    private OrderStatus orderStatus;

    @Column(nullable=false)
    @Builder.Default
    private Integer attempts=0;

    @JdbcTypeCode((SqlTypes.JSON))
    @Column(columnDefinition = "jsonb")
    private Map<String,Object> notes;

    @Column(nullable=false)
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;

}
