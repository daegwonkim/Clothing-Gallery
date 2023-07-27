package com.baegwon.bwm.Repository;

import com.baegwon.bwm.Model.Wish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface WishRepository extends JpaRepository<Wish, Long> {

    @Query(value = "SELECT product_id FROM wish WHERE customer_id = :customer_id", nativeQuery = true)
    List<Long> findByCustomerId(Long customer_id);

    @Query(value = "SELECT EXISTS(SELECT * FROM wish WHERE customer_id = :customer_id and product_id = :product_id)",
            nativeQuery = true)
    Integer isWish(Long customer_id, Long product_id);

    @Modifying
    @Query(value = "DELETE FROM wish WHERE customer_id = :customer_id AND product_id = :product_id",
            nativeQuery = true)
    void deleteWish(Long customer_id, Long product_id);
}
