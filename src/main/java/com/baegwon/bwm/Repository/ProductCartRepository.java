package com.baegwon.bwm.Repository;

import com.baegwon.bwm.Model.ProductCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCartRepository extends JpaRepository<ProductCart, Long> {

    List<ProductCart> findByCartId(Long cart_id);
}
