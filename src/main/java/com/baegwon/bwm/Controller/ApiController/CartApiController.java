package com.baegwon.bwm.Controller.ApiController;

import com.baegwon.bwm.Model.Dto.AddToCartDto;
import com.baegwon.bwm.Model.Dto.ProductCartDto;
import com.baegwon.bwm.Model.ProductCart;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CartApiController {

    // Detail.vue
    @PostMapping("/cart/add")
    public ResponseEntity<?> add(@RequestBody AddToCartDto addToCartDto) {
        cartService.addCartItem(addToCartDto);

        return new ResponseEntity<>(1, HttpStatus.OK);
    }

    // Cart.vue
    @GetMapping("/cart/get/item/{customer_id}")
    public List<ProductCartDto> test5(@PathVariable Long customer_id) {
        List<ProductCart> productCartList = cartService.getCartItems(customer_id);
        List<ProductCartDto> productCartDtoList = productCartList.stream()
                .map(productCart -> modelMapper.map(productCart, ProductCartDto.class)).collect(Collectors.toList());

        return productCartDtoList;
    }

    // Cart.vue
    @DeleteMapping("/cart/delete/item/{id}")
    public void test6(@PathVariable Long id) {
        cartService.deleteCartItem(id);
    }
}
