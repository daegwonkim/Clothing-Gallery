package com.baegwon.bwm.Model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Customer {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String nickname;

    private String phone;
    private String address;

    @Column(name = "membership_level")
    private int membershipLevel;
    private String status;

    @OneToOne(mappedBy = "customer")
    private Cart cart;

    @Column(name = "register_date")
    private LocalDateTime registerDate;

    @Column(name = "modify_date")
    private LocalDateTime modifyDate;

    @PrePersist
    public void registerDate() {
        this.registerDate = LocalDateTime.now();
    }
}
