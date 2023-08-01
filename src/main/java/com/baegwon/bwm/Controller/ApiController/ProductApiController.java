package com.baegwon.bwm.Controller.ApiController;

import com.baegwon.bwm.Model.Dto.ProductDetailDto;
import com.baegwon.bwm.Model.Dto.ProductDto;
import com.baegwon.bwm.Model.Product;
import com.baegwon.bwm.Model.Size;
import com.baegwon.bwm.Repository.ProductRepository;
import com.baegwon.bwm.Service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/api/product")
@RestController
public class ProductApiController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/get/{product_id}")
    public Product getProduct(@PathVariable Long product_id) {
        return productService.getProduct(product_id);
    }

    // Home.vue
    @GetMapping("/get/slider/item/{type}")
    public List<ProductDto> slider(@PathVariable String type) {
        List<Product> productList = productService.getSliderItems(type);

        List<ProductDto> productDtoList = productList.stream().
                map(product -> modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());

        return productDtoList;
    }

    // List.vue
    @GetMapping("/get/list/item/{category}")
    public List<ProductDto> list(@PathVariable String category) {
        List<Product> productList = productService.getListItems(category);

        List<ProductDto> productDtoList = productList.stream()
                .map(product -> modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());

        return productDtoList;
    }

    // Detail.vue
    @GetMapping("/get/detail/{product_id}")
    public ProductDetailDto detail(@PathVariable Long product_id) {
        return productService.getProductDetail(product_id);
    }

    @DeleteMapping("/delete/{product_id}")
    public ResponseEntity<?> delete(@PathVariable Long product_id) {
        productService.deleteProduct(product_id);

        return new ResponseEntity<>(1, HttpStatus.OK);
    }

    // Wish.vue
    @GetMapping("/get/wish-list")
    public List<ProductDto> wish(@RequestParam(value = "productId") List<Long> productIdList) {
        List<Product> productList = productService.getProductForWish(productIdList);

        List<ProductDto> productDtoList = productList.stream()
                .map(product -> modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());

        return productDtoList;
    }
}
