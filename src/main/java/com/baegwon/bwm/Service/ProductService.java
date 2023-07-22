package com.baegwon.bwm.Service;

import com.baegwon.bwm.Model.Dto.ProductDetailDto;
import com.baegwon.bwm.Model.Product;
import com.baegwon.bwm.Model.Size;
import com.baegwon.bwm.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<Product> getSliderItems(String type) {
        List<Product> productList;

        if (type.equals("new")) {
            productList = productRepository.findNewArrivals();
        } else {
            productList = productRepository.findMostPopular();
        }

        return productList;
    }

    @Transactional(readOnly = true)
    public List<Product> getListItems(String category) {
        List<Product> productList;

        if (category.equals("all")) {
            productList = productRepository.findAll(Sort.by(Sort.Direction.DESC, "registerDate"));
        } else if (category.equals("sale")) {
            productList = productRepository.findDiscountProduct();
        } else {
            productList = productRepository.findProductByCategory(category);
        }

        return productList;
    }

    @Transactional(readOnly = true)
    public ProductDetailDto getProductDetail(Long product_id) {
        Product product = productRepository.findById(product_id).orElseThrow(() -> {
            throw new IllegalStateException("상품 정보가 없습니다.");
        });

        return new ProductDetailDto(product, productRepository.findPrevProductById(product_id),
                productRepository.findNextProductById(product_id));
    }
}
