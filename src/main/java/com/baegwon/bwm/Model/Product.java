package com.baegwon.bwm.Model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int price;
    private String url;
    private String brand;

    private int sales;
    private LocalDateTime register_date;

    @PrePersist
    public void registerDate() {
        this.register_date = LocalDateTime.now();
    }

    @Builder
    public Product(String name, int price, String url, int sales) {
        this.name = name;
        this.price = price;
        this.url = url;
        this.sales = sales;
    }
}
