package com.baegwon.bwm.Controller.ApiController;

import com.baegwon.bwm.Model.Customer;
import com.baegwon.bwm.Model.Dto.UpdateInfoDto;
import com.baegwon.bwm.Service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("customer")
@RestController
public class CustomerApiController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/get/{customer_id}")
    public Customer getCustomer(@PathVariable Long customer_id) {
        return customerService.getCustomer(customer_id);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateInfo(@Valid @RequestBody UpdateInfoDto updateInfoDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            Map<String, String> validatorResult = customerService.validateHandling(bindingResult);

            return new ResponseEntity<>(validatorResult, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(customerService.updateInfo(updateInfoDto), HttpStatus.OK);
    }
}
