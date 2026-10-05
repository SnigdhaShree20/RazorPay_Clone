package com.project.razorpay.Merchant.entity;

import com.project.razorpay.Common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="merchant_webhook_config",indexes={@Index(name="idx_webhook_merchant_id",columnList="merchant_id,enabled")})
public class MerchantWebhookConfig extends BaseEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name="merchant_id", nullable = false)
    private Merchant merchant;//who is the merchant

    @Column(nullable = false,length=500)
    private String targetUrl;//www.zara.com/webhook/success  there can be other webooks with failures ,etc
    @Column(length=255)
    private String webhookSecretHash;
    @Column(nullable=false)
    private Boolean enabled=true;
    @Column(length=255)
    private  String eventTypes;// comma separated list of event types to subscribe to


}
