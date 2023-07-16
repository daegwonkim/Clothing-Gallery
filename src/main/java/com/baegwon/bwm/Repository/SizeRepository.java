package com.baegwon.bwm.Repository;

import com.baegwon.bwm.Model.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SizeRepository extends JpaRepository<Size, Long> {

    @Query(value = "SELECT * FROM size WHERE product_id = :product_id", nativeQuery = true)
    List<Size> findSizeByProductId(Long product_id);
}
