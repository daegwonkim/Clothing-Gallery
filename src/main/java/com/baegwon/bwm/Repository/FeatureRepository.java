package com.baegwon.bwm.Repository;

import com.baegwon.bwm.Model.Feature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeatureRepository extends JpaRepository<Feature, Long> {

    @Query(value = "SELECT feature FROM feature WHERE product_id = :product_id", nativeQuery = true)
    List<String> findFeatureByProductId(Long product_id);
}
