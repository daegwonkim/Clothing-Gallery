package com.baegwon.bwm.Repository;

import com.baegwon.bwm.Model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    @Query(value = "SELECT url FROM image WHERE product_id = :product_id", nativeQuery = true)
    List<String> findImageByProductId(Long product_id);
}
