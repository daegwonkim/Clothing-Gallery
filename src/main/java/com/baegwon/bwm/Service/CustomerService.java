package com.baegwon.bwm.Service;

import com.baegwon.bwm.Model.Customer;
import com.baegwon.bwm.Model.Dto.UpdateInfoDto;
import com.baegwon.bwm.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

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

    @Transactional
    public Customer updateInfo(UpdateInfoDto updateInfoDto) {
        Customer customer = customerRepository.findById(updateInfoDto.getId()).orElseThrow(() -> {
            throw new IllegalStateException("사용자 정보를 찾을 수 없습니다.");
        });

        customer.setUsername(updateInfoDto.getUsername());
        customer.setPassword(updateInfoDto.getPassword());
        customer.setNickname(updateInfoDto.getNickname());
        customer.setPhone(updateInfoDto.getPhone());

        return customer;
    }

    @Transactional(readOnly = true)
    public Map<String, String> validateHandling(BindingResult bindingResult) {
        Map<String, String> validatorResult = new HashMap<>();

        for(FieldError error : bindingResult.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }

        return validatorResult;
    }
}
