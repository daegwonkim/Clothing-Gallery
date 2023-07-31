package com.baegwon.bwm.Model;

import com.baegwon.bwm.Model.Embedded.Address;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Customer {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String nickname;

    private String phone;

    @Column(name = "membership_level")
    private int membershipLevel;
    private String status;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE)
    private List<Address> address = new ArrayList<>();

    @OneToOne(mappedBy = "customer", cascade = CascadeType.REMOVE)
    private Cart cart;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE)
    private List<Wish> wish = new ArrayList<>();

    @Column(name = "register_date")
    private LocalDateTime registerDate;

    @Column(name = "modify_date")
    private LocalDateTime modifyDate;

    @PrePersist
    public void registerDate() {
        this.registerDate = LocalDateTime.now();
    }
}
