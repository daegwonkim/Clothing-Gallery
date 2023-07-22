package com.baegwon.bwm.Controller.ApiController;

import com.baegwon.bwm.Model.Dto.FeatureDetailDto;
import com.baegwon.bwm.Model.Dto.SizeDetailDto;
import com.baegwon.bwm.Service.FeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeatureApiController {

    @Autowired
    private FeatureService featureService;

    // Detail.vue
    @GetMapping("/feature/get/detail/{product_id}")
    public FeatureDetailDto detail(@PathVariable Long product_id) throws Exception {
        return featureService.getFeatureDetail(product_id);
    }
}
