package com.baegwon.bwm.Model.Dto;

import com.baegwon.bwm.Model.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SizeDetailDto {

    List<Size> sizes = new ArrayList<>();
}
