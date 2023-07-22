package com.baegwon.bwm.Service;

import com.baegwon.bwm.Model.Dto.SizeDetailDto;
import com.baegwon.bwm.Model.Size;
import com.baegwon.bwm.Repository.SizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SizeService {

    @Autowired
    private SizeRepository sizeRepository;

    @Transactional(readOnly = true)
    public SizeDetailDto getSizeDetail(Long product_id) {
        return new SizeDetailDto(sizeRepository.findSizeByProductId(product_id));
    }
}
