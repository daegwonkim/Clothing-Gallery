package com.baegwon.bwm.Repository;

import com.baegwon.bwm.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT * FROM product ORDER BY register_date DESC LIMIT 16", nativeQuery = true)
    List<Product> findNewArrivals();

    @Query(value = "SELECT * FROM product ORDER BY sales DESC LIMIT 16", nativeQuery = true)
    List<Product> findMostPopular();

    @Query(value = "SELECT * FROM product WHERE category = :category", nativeQuery = true)
    List<Product> findProductByCategory(String category);

    @Query(value = "SELECT * FROM product WHERE isDiscount = 1", nativeQuery = true)
    List<Product> findDiscountProduct();
}
