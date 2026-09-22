package com.project.razorpay.Merchant.entity;

import com.project.razorpay.Common.enums.BusinessType;
import com.project.razorpay.Common.enums.MerchantStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "merchant")
public class Merchant {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false,length=100)
    private String name;
    @Column(unique=true,nullable=false)
    private String email;
    @Column(length=10,nullable=false)
    private String contactNumber;

    @Column(length=50)
    @Enumerated(EnumType.STRING)
    private BusinessType businessType;
    @Column(length=100)
    private String businessName;
    @Column(length=100)
    private String website_url;

    @Column(length=100,nullable=false)
    @Enumerated(EnumType.STRING)
    private MerchantStatus status=MerchantStatus.PENDING_KYC;
    @Column(length=10)
    private String gstId;
    @Column(length=10)
    private String panId;

    private String SettlementBankAccount;
    private String SettlementBankName;
    private String SettlementBankIfsc;
    private String SettlementBankAccountHolderName;


}