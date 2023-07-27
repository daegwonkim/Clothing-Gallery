package com.baegwon.bwm.Controller.ApiController;

import com.baegwon.bwm.Model.Dto.WishDto;
import com.baegwon.bwm.Model.Dto.ProductDto;
import com.baegwon.bwm.Service.WishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("wish")
@RestController
public class WishApiController {

    @Autowired
    private WishService wishService;

    // Detail.vue
    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody WishDto wishDto) {
        wishService.addWish(wishDto);

        return new ResponseEntity<>(1, HttpStatus.OK);
    }

    // Wish.vue
    @GetMapping("/get/item/{customer_id}")
    public List<Long> getWishList(@PathVariable Long customer_id) {
        return wishService.getWishList(customer_id);
    }

    // Detail.vue
    @GetMapping("/get/{product_id}/state/{customer_id}")
    public Integer isWish(@PathVariable Long product_id, @PathVariable Long customer_id) {
        return wishService.isWish(customer_id, product_id);
    }

    @DeleteMapping("/delete/{product_id}/{customer_id}")
    public ResponseEntity<?> delete(@PathVariable Long product_id, @PathVariable Long customer_id) {
        wishService.delete(customer_id, product_id);

        return new ResponseEntity<>(1, HttpStatus.OK);
    }
}
