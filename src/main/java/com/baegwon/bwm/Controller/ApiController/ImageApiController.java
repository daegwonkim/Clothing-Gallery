package com.baegwon.bwm.Controller.ApiController;

import com.baegwon.bwm.Service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ImageApiController {

    @Autowired
    private ImageService imageService;

    // Detail.vue
    @GetMapping("/image/get/detail/{product_id}")
    public List<String> detail(@PathVariable Long product_id) {
        return imageService.getImageDetail(product_id);
    }
}
