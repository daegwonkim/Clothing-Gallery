package com.baegwon.bwm.Model.Dto;

import com.baegwon.bwm.Model.Feature;
import com.baegwon.bwm.Model.Image;
import com.baegwon.bwm.Model.Product;
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
public class ProductDetailDTO {

    private Product product;
    private List<Size> sizes = new ArrayList<>();
    private List<String> images = new ArrayList<>();
    private List<String> features = new ArrayList<>();
    private Long prevProduct;
    private Long nextProduct;
}
