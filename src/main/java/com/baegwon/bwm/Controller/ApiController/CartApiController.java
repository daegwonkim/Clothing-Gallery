package com.baegwon.bwm.Controller.ApiController;

import com.baegwon.bwm.Model.Dto.AddToCartDto;
import com.baegwon.bwm.Model.Dto.ProductCartDto;
import com.baegwon.bwm.Model.ProductCart;
import com.baegwon.bwm.Service.CartService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/api/cart")
@RestController
public class CartApiController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ModelMapper modelMapper;

    // Detail.vue
    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody AddToCartDto addToCartDto) {
        cartService.addCartItem(addToCartDto);

        return new ResponseEntity<>(1, HttpStatus.OK);
    }

    // Cart.vue
    @GetMapping("/get/item/{customer_id}")
    public List<ProductCartDto> getCartItems(@PathVariable Long customer_id) {
        List<ProductCart> productCartList = cartService.getCartItems(customer_id);
        List<ProductCartDto> productCartDtoList = productCartList.stream()
                .map(productCart -> modelMapper.map(productCart, ProductCartDto.class)).collect(Collectors.toList());

        return productCartDtoList;
    }

    // Cart.vue
    @DeleteMapping("/delete/item/{productCart_id}")
    public void deleteCartItem(@PathVariable Long productCart_id) {
        cartService.deleteCartItem(productCart_id);
    }
}
