package com.baegwon.bwm.Service;

import com.baegwon.bwm.Model.Customer;
import com.baegwon.bwm.Model.Dto.AddressDto;
import com.baegwon.bwm.Model.Embedded.Address;
import com.baegwon.bwm.Repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Transactional(readOnly = true)
    public List<Address> getAddress(Long customer_id) {
        return addressRepository.findAllByCustomerId(customer_id);
    }

    @Transactional
    public void addAddress(Customer customer, AddressDto addressDto) {
        if(addressDto.isDefaultAddress()) {
            changeDefaultAddress();
        }

        Address address = Address.builder()
                .name(addressDto.getName())
                .zipcode(addressDto.getZipcode())
                .street(addressDto.getStreet())
                .detail(addressDto.getDetail())
                .defaultAddress(addressDto.isDefaultAddress())
                .customer(customer)
                .build();

        addressRepository.save(address);
    }

    @Transactional
    public void changeDefaultAddress() {
        Address defaultAddress = addressRepository.findByDefaultAddress();
        if(defaultAddress == null) return;
        defaultAddress.setDefaultAddress(false);
    }

    @Transactional
    public void editAddress(AddressDto addressDto) {
        if(addressDto.isDefaultAddress()) {
            changeDefaultAddress();
        }

        Address address = addressRepository.findById(addressDto.getId()).orElseThrow(() -> {
            throw new IllegalStateException("주소 정보를 찾을 수 없습니다.");
        });

        address.setName(addressDto.getName());
        address.setZipcode(addressDto.getZipcode());
        address.setStreet(addressDto.getStreet());
        address.setDetail(addressDto.getDetail());
        address.setDefaultAddress(addressDto.isDefaultAddress());
    }

    @Transactional
    public void deleteAddress(Long address_id) {
        addressRepository.deleteById(address_id);
    }
}
