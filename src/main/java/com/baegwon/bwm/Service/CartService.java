package com.baegwon.bwm.Service;

import com.baegwon.bwm.Model.Cart;
import com.baegwon.bwm.Model.Dto.AddToCartDto;
import com.baegwon.bwm.Model.Product;
import com.baegwon.bwm.Model.ProductCart;
import com.baegwon.bwm.Repository.CartRepository;
import com.baegwon.bwm.Repository.CustomerRepository;
import com.baegwon.bwm.Repository.ProductCartRepository;
import com.baegwon.bwm.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private ProductCartRepository productCartRepository;

    @Autowired
    private CartRepository cartRepository;

    @Transactional
    public void addCartItem(AddToCartDto addToCartDto) {
        ProductCart productCart = ProductCart.builder()
                .cart(cartRepository.findByCustomerId(addToCartDto.getCustomerId()))
                .product(addToCartDto.getProduct())
                .size(addToCartDto.getSize())
                .quantity(addToCartDto.getQuantity())
                .totalPrice(addToCartDto.getProduct().getPrice() * addToCartDto.getQuantity())
                .build();

        productCartRepository.save(productCart);
    }

    @Transactional(readOnly = true)
    public List<ProductCart> getCartItems(Long customer_id) {
        Cart cart = cartRepository.findByCustomerId(customer_id);
        List<ProductCart> productCartList = productCartRepository.findByCartId(cart.getId());

        return productCartList;
    }

    @Transactional
    public void deleteCartItem(Long productCart_id) {
        productCartRepository.deleteById(productCart_id);
    }
}
