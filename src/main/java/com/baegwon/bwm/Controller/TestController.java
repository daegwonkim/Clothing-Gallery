package com.baegwon.bwm.Controller;

import com.baegwon.bwm.Model.Dto.ProductDetailDTO;
import com.baegwon.bwm.Model.Product;
import com.baegwon.bwm.Model.Dto.ProductDto;
import com.baegwon.bwm.Model.Size;
import com.baegwon.bwm.Repository.FeatureRepository;
import com.baegwon.bwm.Repository.ImageRepository;
import com.baegwon.bwm.Repository.ProductRepository;
import com.baegwon.bwm.Repository.SizeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
            productList = productRepository.findAll(Sort.by(Sort.Direction.DESC, "registerDate"));
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
    public ProductDetailDTO test3(@PathVariable Long product_id) throws Exception {
        // 1. Get Product
        Product product = productRepository.findById(product_id).orElseThrow(() -> {
            throw new IllegalStateException("상품 정보가 없습니다.");
        });

        ProductDetailDTO productDetailDTO = new ProductDetailDTO();
        productDetailDTO.setProduct(product);

        // 2. Get Product Sizes
        List<Size> sizes = sizeRepository.findSizeByProductId(product_id);
        productDetailDTO.setSizes(sizes);

        // 3. Get Product Images
        productDetailDTO.setImages(imageRepository.findImageByProductId(product_id));

        // 4. Get Product Features
        productDetailDTO.setFeatures(featureRepository.findFeatureByProductId(product_id));

        // 5. Get Prev and Next Product
        productDetailDTO.setPrevProduct(productRepository.findPrevProductById(product_id));
        productDetailDTO.setNextProduct(productRepository.findNextProductById(product_id));

        return productDetailDTO;
    }
}
