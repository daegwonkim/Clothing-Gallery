package com.baegwon.bwm.Controller;

import com.baegwon.bwm.Model.ProductDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TestController {

    private List<ProductDto> productDtoList;

    @GetMapping("/test")
    public List<ProductDto> test() {
        productDtoList = new ArrayList<>();

        String names[] = {
                "A.P.C. Augustino SS Polo Shirt | beige",
                "A.P.C. Hunt SS Shirt | khaki green",
                "A.P.C. Petite New Standard Jeans | washed light blue",
                "A.P.C. New Standard Jeans | washed indigo",
                "A.P.C. Martin Jeans | washed light blue",
                "A.P.C. Madame Logo T-Shirt | heather grey/green",
                "A.P.C. Jeremy T-Shirt | navy/white",
                "A.P.C. Jeremy T-Shirt | white/navy",
                "Honor the Gift Tobacco Print Shirt | mustard",
                "Honor the Gift Tobacco Print Shirt | black",
                "Honor the Gift Canvas Shorts | black",
                "Honor the Gift Tobacco Field T-Shirt | bone",
                "Honor the Gift Tobacco Field T-Shirt | black",
                "Pleasures Heart Short | black",
                "Pleasures Coffer Shorts | blue",
                "Pleasures Despair Heavyweight T-Shirt"
        };

        int prices[] = { 195000, 230000, 270000, 270000, 270000, 150000, 150000, 150000, 110000, 110000, 120000, 65000,
                65000, 90000, 100000, 60000 };

        String urls[] = { "clothing1", "clothing2", "clothing3", "clothing4", "clothing5", "clothing6", "clothing7",
                "clothing8", "clothing9", "clothing10", "clothing11", "clothing12", "clothing13", "clothing14",
                "clothing15", "clothing16" };

        for (int i = 0; i < 16; i++) {
            productDtoList.add(new ProductDto(names[i], prices[i], urls[i]));
        }

        return productDtoList;
    }
}
