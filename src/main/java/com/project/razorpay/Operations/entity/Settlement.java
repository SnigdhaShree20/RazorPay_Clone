package com.project.razorpay.Operations.entity;


import com.project.razorpay.Common.entity.Money;
import com.project.razorpay.Common.enums.SettlementStatus;
import com.project.razorpay.Merchant.entity.Merchant;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="settlement")
public class Settlement {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable=false)
    private UUID merchantId;

    @Embedded
    private Money grossAmount;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="amountUnits", column= @Column(name="refund_amount_units",nullable=false)),
            @AttributeOverride(name="currency",column= @Column(name= "refund_amount_currency",nullable=false))
    })
    private Money refundAmount;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="amountUnits", column= @Column(name="fee_amount_units",nullable=false)),
            @AttributeOverride(name="currency",column= @Column(name= "fee_amount_currency",nullable=false))
    })
    private Money feeAmount;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="amountUnits", column= @Column(name="gst_amount_units",nullable=false)),
            @AttributeOverride(name="currency",column= @Column(name= "gst_amount_currency",nullable=false))
    })
    private Money gstAmount;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="amountUnits", column= @Column(name="net_amount_units",nullable=false)),
            @AttributeOverride(name="currency",column= @Column(name= "net_amount_currency",nullable=false))
    })
    private Money netAmount;


    private SettlementStatus status;
    private String bankReference;
    private LocalDateTime processedAt;

}
