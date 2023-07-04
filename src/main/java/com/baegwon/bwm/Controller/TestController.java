package com.baegwon.bwm.Controller;

import com.baegwon.bwm.Model.Product;
import com.baegwon.bwm.Model.ProductDto;
import com.baegwon.bwm.Repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TestController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/test/{type}")
    public List<ProductDto> test(@PathVariable String type) {
        ModelMapper modelMapper = new ModelMapper();

        List<Product> productList;
        if (type.equals("new")) {
            productList = productRepository.findNewArrivals();
        } else {
            productList = productRepository.findMostPopular();
        }

        List<ProductDto> productDtoList = productList.stream().
                map(product -> modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());

        return productDtoList;
    }
}
