package com.baegwon.bwm.Controller.ApiController;

import com.baegwon.bwm.Service.FeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/feature")
@RestController
public class FeatureApiController {

    @Autowired
    private FeatureService featureService;

    // Detail.vue
    @GetMapping("/get/detail/{product_id}")
    public List<String> detail(@PathVariable Long product_id) throws Exception {
        return featureService.getFeatureDetail(product_id);
    }
}
