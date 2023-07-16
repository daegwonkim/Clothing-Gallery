package com.baegwon.bwm.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String thumbnail;
    private int price;

    private String category;
    private String brand;

    private int sales;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "is_discount")
    @ColumnDefault("false")
    private boolean isDiscount;

    @Column(name = "discount_price")
    @ColumnDefault("'0'")
    private int discountPrice;

    @OneToMany(mappedBy = "product")
    private List<Size> sizes = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<Image> images = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<Feature> features = new ArrayList<>();

    @Column(name = "register_date")
    private LocalDateTime registerDate;

    @PrePersist
    public void registerDate() {
        this.registerDate = LocalDateTime.now();
    }
}
