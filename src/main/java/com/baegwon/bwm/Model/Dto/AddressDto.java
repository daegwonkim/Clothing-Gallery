package com.baegwon.bwm.Model.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {

    private Long id;
    private String name;
    private String zipcode;
    private String street;
    private String detail;
    private boolean defaultAddress;
}
