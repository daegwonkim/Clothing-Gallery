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

    ModelMapper modelMapper = new ModelMapper();

    @GetMapping("/test/{type}")
    public List<ProductDto> test(@PathVariable String type) {
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

    @GetMapping("/test2/{category}")
    public List<ProductDto> test2(@PathVariable String category) {
        List<Product> productList = null;
        if (category.equals("all")) {
            productList = productRepository.findAll();
        } else if (category.equals("sale")) {
            productList = productRepository.findDiscountProduct();
        } else {
            productList = productRepository.findProductByCategory(category);
        }

        List<ProductDto> productDtoList = productList.stream().
                map(product -> modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());

        return productDtoList;
    }
}
