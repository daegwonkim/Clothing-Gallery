package com.baegwon.bwm.Controller.ApiController;

import com.baegwon.bwm.Model.Dto.ProductDetailDto;
import com.baegwon.bwm.Model.Dto.SizeDetailDto;
import com.baegwon.bwm.Service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SizeApiController {

    @Autowired
    private SizeService sizeService;

    // Detail.vue
    @GetMapping("/size/get/detail/{product_id}")
    public SizeDetailDto detail(@PathVariable Long product_id) throws Exception {
        return sizeService.getSizeDetail(product_id);
    }
}
