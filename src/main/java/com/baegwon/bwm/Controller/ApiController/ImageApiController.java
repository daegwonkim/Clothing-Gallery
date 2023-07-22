package com.baegwon.bwm.Controller.ApiController;

import com.baegwon.bwm.Model.Dto.ImageDetailDto;
import com.baegwon.bwm.Model.Dto.SizeDetailDto;
import com.baegwon.bwm.Service.ImageService;
import com.baegwon.bwm.Service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImageApiController {

    @Autowired
    private ImageService imageService;

    // Detail.vue
    @GetMapping("/image/get/detail/{product_id}")
    public ImageDetailDto detail(@PathVariable Long product_id) throws Exception {
        return imageService.getImageDetail(product_id);
    }
}
