package com.baegwon.bwm.Controller;

import com.baegwon.bwm.Model.Dto.SizeDto;
import com.baegwon.bwm.Model.Product;
import com.baegwon.bwm.Model.Dto.ProductDto;
import com.baegwon.bwm.Model.Size;
import com.baegwon.bwm.Repository.FeatureRepository;
import com.baegwon.bwm.Repository.ImageRepository;
import com.baegwon.bwm.Repository.ProductRepository;
import com.baegwon.bwm.Repository.SizeRepository;
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

    @Autowired
    private SizeRepository sizeRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private FeatureRepository featureRepository;

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

        List<ProductDto> productDtoList = productList.stream()
                .map(product -> modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());

        return productDtoList;
    }

    @GetMapping("/test3/{product_id}")
    public List<SizeDto> test3(@PathVariable Long product_id) {
        List<Size> sizeList = sizeRepository.findSizeByProductId(product_id);
        List<SizeDto> sizeDtoList = sizeList.stream()
                .map(size -> modelMapper.map(size, SizeDto.class)).collect(Collectors.toList());

        return sizeDtoList;
    }

    @GetMapping("/test4/{product_id}")
    public List<String> test4(@PathVariable Long product_id) {
        return imageRepository.findImageByProductId(product_id);
    }

    @GetMapping("/test5/{product_id}")
    public ProductDto test5(@PathVariable Long product_id) throws Exception {
        Product product = productRepository.findById(product_id).orElseThrow(() -> {
            throw new IllegalStateException("상품 정보가 없습니다.");
        });

        ProductDto productDto = modelMapper.map(product, ProductDto.class);

        return productDto;
    }

    @GetMapping("/test6/{product_id}")
    public List<String> test6(@PathVariable Long product_id) {
        return featureRepository.findFeatureByProductId(product_id);
    }
}
