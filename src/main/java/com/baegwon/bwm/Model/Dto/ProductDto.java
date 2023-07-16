package com.baegwon.bwm.Model.Dto;

import com.baegwon.bwm.Model.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private Long id;
    private String name;
    private int price;
    private String thumbnail;
    private String category;
    private String brand;
    private int sales;
    private String description;
    private boolean isDiscount;
    private int discountPrice;
    private LocalDateTime registerDate;
}