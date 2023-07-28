package com.baegwon.bwm.Model.Dto;

import com.baegwon.bwm.Model.Customer;
import com.baegwon.bwm.Model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WishDto {

    private Customer customer;
    private Product product;
}
