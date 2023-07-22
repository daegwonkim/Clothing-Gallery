package com.baegwon.bwm.Service;

import com.baegwon.bwm.Repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Transactional(readOnly = true)
    public List<String> getImageDetail(Long product_id) {
        return imageRepository.findImageByProductId(product_id);
    }
}
