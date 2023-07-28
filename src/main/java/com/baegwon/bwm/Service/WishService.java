package com.baegwon.bwm.Service;

import com.baegwon.bwm.Model.Dto.WishDto;
import com.baegwon.bwm.Model.Product;
import com.baegwon.bwm.Model.Wish;
import com.baegwon.bwm.Repository.CustomerRepository;
import com.baegwon.bwm.Repository.ProductRepository;
import com.baegwon.bwm.Repository.WishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WishService {

    @Autowired
    private WishRepository wishRepository;

    @Transactional
    public void addWish(WishDto wishDto) {
        Wish wish = Wish.builder()
                        .customer(wishDto.getCustomer())
                        .product(wishDto.getProduct())
                        .build();

        wishRepository.save(wish);
    }

    @Transactional(readOnly = true)
    public List<Long> getWishList(Long customer_id) {
        return wishRepository.findByCustomerId(customer_id);
    }

    @Transactional(readOnly = true)
    public Integer isWish(Long customer_id, Long product_id) {
        return wishRepository.isWish(customer_id, product_id);
    }

    @Transactional
    public void delete(Long customer_id, Long product_id) {
        wishRepository.deleteWish(customer_id, product_id);
    }
}
