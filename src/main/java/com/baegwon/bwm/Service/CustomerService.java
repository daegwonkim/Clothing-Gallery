package com.baegwon.bwm.Service;

import com.baegwon.bwm.Model.Customer;
import com.baegwon.bwm.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Transactional(readOnly = true)
    public Customer getCustomer(Long customer_id) {
        return customerRepository.findById(customer_id).orElseThrow(() -> {
            throw new IllegalStateException("사용자 정보를 찾을 수 없습니다.");
        });
    }
}
