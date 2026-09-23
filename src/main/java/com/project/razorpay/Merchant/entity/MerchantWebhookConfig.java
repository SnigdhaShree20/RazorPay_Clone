package com.project.razorpay.Merchant.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="merchant_webhook_config")
public class MerchantWebhookConfig {

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
