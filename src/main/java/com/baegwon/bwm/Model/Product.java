package com.baegwon.bwm.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Size> sizes = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Image> images = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Feature> features = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<ProductCart> productCarts = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Wish> wish = new ArrayList<>();

    @Column(name = "register_date")
    private LocalDateTime registerDate;

    @PrePersist
    public void registerDate() {
        this.registerDate = LocalDateTime.now();
    }
}
