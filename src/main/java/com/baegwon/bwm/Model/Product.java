package com.baegwon.bwm.Model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

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

    private String category;
    private String brand;

    private int sales;

    @ColumnDefault("false")
    private boolean isDiscount;

    @ColumnDefault("'0'")
    private int discountPrice;

    private LocalDateTime register_date;

    @PrePersist
    public void registerDate() {
        this.register_date = LocalDateTime.now();
    }
}
