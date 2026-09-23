package com.project.razorpay.Merchant.entity;

import com.project.razorpay.Common.enums.Environment;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiKey {

    //when we create accounts for merchants we creates API_key which will be used to communicate
    //to the servers(abstraction for real merchant Name")

    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    private UUID uuid;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name="merchant_id",nullable=false)
    private Merchant merchant;

    @Column(nullable=false,length=50,unique=true)
    private String keyId;

    @Column(nullable=false,length=150)
    private String keySecretHash;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false,length=50)
    private Environment environment;

    @Column(nullable=false)
    private boolean enabled=true;

    private LocalDateTime lastUsedAt;
    private LocalDateTime rotatedAt;
    private LocalDateTime gracePeriodExpiresAt;

}
