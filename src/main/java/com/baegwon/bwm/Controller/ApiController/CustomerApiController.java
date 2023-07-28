package com.baegwon.bwm.Controller.ApiController;

import com.baegwon.bwm.Model.Customer;
import com.baegwon.bwm.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("customer")
@RestController
public class CustomerApiController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/get/{customer_id}")
    public Customer getCustomer(@PathVariable Long customer_id) {
        return customerService.getCustomer(customer_id);
    }
}
