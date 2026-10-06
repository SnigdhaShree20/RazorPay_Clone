package com.project.razorpay.Operations.entity;


import com.project.razorpay.Common.entity.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name= "settlement_payment")
public class SettlementPayment extends BaseEntity {

    @EmbeddedId
    private SettlementPaymentId id;

    @MapsId("settlementId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "settlement_id", nullable = false)
    private Settlement settlement;
}
