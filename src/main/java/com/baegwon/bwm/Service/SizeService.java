package com.baegwon.bwm.Service;

import com.baegwon.bwm.Model.Dto.ProductDto;
import com.baegwon.bwm.Model.Dto.SizeDetailDto;
import com.baegwon.bwm.Model.Size;
import com.baegwon.bwm.Repository.SizeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SizeService {

    @Autowired
    private SizeRepository sizeRepository;

    @Transactional(readOnly = true)
    public List<Size> getSizeDetail(Long product_id) {
        return sizeRepository.findByProductId(product_id);
    }
}
