package com.baegwon.bwm.Model.Dto;

import com.baegwon.bwm.Model.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SizeDetailDto {

    private String size;
    private int stock;
}
