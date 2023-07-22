package com.baegwon.bwm.Service;

import com.baegwon.bwm.Model.Dto.FeatureDetailDto;
import com.baegwon.bwm.Repository.FeatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FeatureService {

    @Autowired
    private FeatureRepository featureRepository;

    @Transactional(readOnly = true)
    public FeatureDetailDto getFeatureDetail(Long product_id) {
        return new FeatureDetailDto(featureRepository.findFeatureByProductId(product_id));
    }
}
