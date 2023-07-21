package com.baegwon.bwm.Controller;

import com.baegwon.bwm.Model.Dto.AddToCartDto;
import com.baegwon.bwm.Model.Dto.ProductCartDto;
import com.baegwon.bwm.Model.Dto.ProductDetailDto;
import com.baegwon.bwm.Model.Product;
import com.baegwon.bwm.Model.Dto.ProductDto;
import com.baegwon.bwm.Model.ProductCart;
import com.baegwon.bwm.Model.Size;
import com.baegwon.bwm.Repository.FeatureRepository;
import com.baegwon.bwm.Repository.ImageRepository;
import com.baegwon.bwm.Repository.ProductRepository;
import com.baegwon.bwm.Repository.SizeRepository;
import com.baegwon.bwm.Service.CartService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private CartService cartService;

    ModelMapper modelMapper = new ModelMapper();

    // Slider.vue
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

    // List.vue
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

    // Detail.vue
    @GetMapping("/test3/{product_id}")
    public ProductDetailDto test3(@PathVariable Long product_id) throws Exception {
        // 1. Get Product
        Product product = productRepository.findById(product_id).orElseThrow(() -> {
            throw new IllegalStateException("상품 정보가 없습니다.");
        });

        ProductDetailDto productDetailDto = new ProductDetailDto();
        productDetailDto.setProduct(product);

        // 2. Get Product Sizes
        List<Size> sizes = sizeRepository.findSizeByProductId(product_id);
        productDetailDto.setSizes(sizes);

        // 3. Get Product Images
        productDetailDto.setImages(imageRepository.findImageByProductId(product_id));

        // 4. Get Product Features
        productDetailDto.setFeatures(featureRepository.findFeatureByProductId(product_id));

        // 5. Get Prev and Next Product
        productDetailDto.setPrevProduct(productRepository.findPrevProductById(product_id));
        productDetailDto.setNextProduct(productRepository.findNextProductById(product_id));

        return productDetailDto;
    }

    // Detail.vue
    @PostMapping("/test4")
    public ResponseEntity<?> test4(@RequestBody AddToCartDto addToCartDto) {
        cartService.add(addToCartDto);

        return new ResponseEntity<>(1, HttpStatus.OK);
    }

    // Cart.vue
    @GetMapping("/test5/{customer_id}")
    public List<ProductCartDto> test5(@PathVariable Long customer_id) {
        List<ProductCart> productCartList = cartService.getCartItems(customer_id);
        List<ProductCartDto> productCartDtoList = productCartList.stream()
                .map(productCart -> modelMapper.map(productCart, ProductCartDto.class)).collect(Collectors.toList());

        return productCartDtoList;
    }
}
