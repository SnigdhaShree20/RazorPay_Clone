package com.project.razorpay.Merchant.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

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
