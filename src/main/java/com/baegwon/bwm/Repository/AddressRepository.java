package com.baegwon.bwm.Repository;

import com.baegwon.bwm.Model.Embedded.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query(value = "SELECT * FROM address WHERE customer_id = :customer_id ORDER BY is_default_address DESC",
        nativeQuery = true)
    List<Address> findAllByCustomerId(Long customer_id);

    @Query(value = "SELECT * FROM address WHERE is_default_address = true", nativeQuery = true)
    Address findByDefaultAddress();
}
