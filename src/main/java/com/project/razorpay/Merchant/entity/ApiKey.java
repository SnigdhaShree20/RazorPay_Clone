package com.project.razorpay.Merchant.entity;

import com.project.razorpay.Common.entity.BaseEntity;
import com.project.razorpay.Common.enums.Environment;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="api_key",
indexes = {@Index(name="idx_api_key_merchant_id",columnList = "merchant_id"),
        @Index(name="idx_api_key_merchant_env",columnList="merchant_id,environment,enabled")
}
)
public class ApiKey extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "merchant_id", nullable = false)
    private Merchant merchant;

    @Column(nullable = false, length = 150, unique = true)
    private String keyId;

    @Column(nullable = false, length = 150)
    private String keySecretHash;

    @Column(length = 150)
    private String prevKeySecretHash;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private Environment environment;

    @Column(nullable = false)
    @Builder.Default
    private boolean enabled = true;


    @Column(nullable = false)
    private LocalDateTime createdAt;
    private LocalDateTime lastUsedAt;
    private LocalDateTime rotatedAt;
    private LocalDateTime gracePeriodExpiresAt;


}