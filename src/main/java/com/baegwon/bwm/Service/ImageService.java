package com.baegwon.bwm.Service;

import com.baegwon.bwm.Model.Dto.ImageDetailDto;
import com.baegwon.bwm.Repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Transactional(readOnly = true)
    public ImageDetailDto getImageDetail(Long product_id) {
        return new ImageDetailDto(imageRepository.findImageByProductId(product_id));
    }
}
