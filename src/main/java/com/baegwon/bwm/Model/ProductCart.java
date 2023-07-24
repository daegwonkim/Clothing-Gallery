package com.baegwon.bwm.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class ProductCart {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String size;
    private int quantity;

    @Column(name = "total_price")
    private int totalPrice;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;
}
