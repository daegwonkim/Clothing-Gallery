package com.baegwon.bwm.Model.Dto;

import com.baegwon.bwm.Model.Image;
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
public class ImageDetailDto {

    List<String> images = new ArrayList<>();
}
