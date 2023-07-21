package com.baegwon.bwm.Model.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddToCartDto {

    private Long customerId;
    private Long productId;
    private String size;
    private int quantity;
}
