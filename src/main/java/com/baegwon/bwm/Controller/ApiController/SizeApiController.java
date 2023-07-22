package com.baegwon.bwm.Controller.ApiController;

import com.baegwon.bwm.Model.Dto.ProductDetailDto;
import com.baegwon.bwm.Model.Dto.ProductDto;
import com.baegwon.bwm.Model.Dto.SizeDetailDto;
import com.baegwon.bwm.Model.Product;
import com.baegwon.bwm.Model.Size;
import com.baegwon.bwm.Service.SizeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SizeApiController {

    @Autowired
    private SizeService sizeService;

    @Autowired
    private ModelMapper modelMapper;

    // Detail.vue
    @GetMapping("/size/get/detail/{product_id}")
    public List<SizeDetailDto> detail(@PathVariable Long product_id) {
        List<Size> sizeList = sizeService.getSizeDetail(product_id);

        List<SizeDetailDto> sizeDetailDtoList = sizeList.stream().
                map(size -> modelMapper.map(size, SizeDetailDto.class)).collect(Collectors.toList());

        return sizeDetailDtoList;
    }
}
