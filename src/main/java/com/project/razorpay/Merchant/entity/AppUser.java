package com.project.razorpay.Merchant.entity;


import com.project.razorpay.Common.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="app_user")
public class AppUser {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="merchant_id", nullable=false)
    private Merchant merchant;

    @Column(unique=true, nullable=false)
    private String email;
    @Column(nullable=false)
    private String passwordHash;
    @Column(nullable=false)
    @Enumerated(EnumType.STRING)
    private UserRole role;


}
