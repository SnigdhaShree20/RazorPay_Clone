package com.project.razorpay.Vault.entity;

import com.project.razorpay.Common.enums.RefundStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="card_token")
public class CardToken {

    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    private UUID id;

    @Column(nullable=false,length=50,unique=true)
    private String token;//tokenization of the vault card.So from the token noone can know what is the pan details

    @ManyToOne(fetch=FetchType.LAZY,optional=false)
    @JoinColumn(name="vault_card_id",nullable=false)
    private VaultCard vaultCard;

    @Column(nullable=false)
    private  UUID customer;

    @Column(nullable=false)
    private UUID merchant;

    private LocalDateTime revokedAt;

}
