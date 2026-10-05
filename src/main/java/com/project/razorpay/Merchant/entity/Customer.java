package com.project.razorpay.Merchant.entity;

import com.project.razorpay.Common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name= "customer",indexes={
        @Index(name="idx_customer_merchant_id",columnList="merchant_id"),
        @Index(name="idx_customer_email",columnList="email")
})
public class Customer extends BaseEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    private UUID uuid;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    private Merchant merchant;
    @Column(nullable=false,length=100  )
    private String name;
    @Column(nullable=false,length=100)
    private String email;
    @Column(nullable=false,length=10)
    private String contactNumber;

    private LocalDateTime deletedAt;

}
