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

    @Query(value = "SELECT * FROM product WHERE category = :category ORDER BY register_date DESC", nativeQuery = true)
    List<Product> findProductByCategory(String category);

    @Query(value = "SELECT * FROM product WHERE is_discount = 1 ORDER BY register_date DESC", nativeQuery = true)
    List<Product> findDiscountProduct();

    @Query(value = "SELECT prev_no FROM "
            + "(SELECT id, LAG(id, 1, -1) OVER(ORDER BY id DESC) AS prev_no FROM product) P "
            + "WHERE id = :product_id", nativeQuery = true)
    Long findPrevProductById(Long product_id);

    @Query(value = "SELECT next_no FROM "
            + "(SELECT id, LEAD(id, 1, -1) OVER(ORDER BY id DESC) AS next_no FROM product) P "
            + "WHERE id = :product_id", nativeQuery = true)
    Long findNextProductById(Long product_id);
}
