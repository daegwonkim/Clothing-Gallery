package com.baegwon.bwm.Controller.ApiController;

import com.baegwon.bwm.Model.Customer;
import com.baegwon.bwm.Model.Dto.AddressDto;
import com.baegwon.bwm.Model.Dto.TestDto;
import com.baegwon.bwm.Model.Embedded.Address;
import com.baegwon.bwm.Service.AddressService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("address")
@RestController
public class AddressApiController {

    @Autowired
    private AddressService addressService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/get/{customer_id}")
    public List<AddressDto> getAddress(@PathVariable Long customer_id) {
        List<Address> addressList = addressService.getAddress(customer_id);

        List<AddressDto> addressDtoList = addressList.stream().
                map(address -> modelMapper.map(address, AddressDto.class)).collect(Collectors.toList());

        return addressDtoList;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addAddress(@RequestBody ObjectNode objectNode) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        Customer customer = mapper.treeToValue(objectNode.get("customer"), Customer.class);
        AddressDto address = mapper.treeToValue(objectNode.get("address"), AddressDto.class);

        addressService.addAddress(customer, address);

        return new ResponseEntity<>(1, HttpStatus.OK);
    }

    @PostMapping("/edit")
    public ResponseEntity<?> editAddress(@RequestBody AddressDto addressDto) {
        addressService.editAddress(addressDto);

        return new ResponseEntity<>(1, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{address_id}")
    public void deleteAddress(@PathVariable Long address_id) {
        addressService.deleteAddress(address_id);
    }
}
