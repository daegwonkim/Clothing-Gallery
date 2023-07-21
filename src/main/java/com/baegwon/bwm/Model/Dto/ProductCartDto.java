package com.baegwon.bwm.Model.Dto;

import com.baegwon.bwm.Model.Cart;
import com.baegwon.bwm.Model.Product;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductCartDto {

    private String size;
    private int quantity;
    private Product product;
}
