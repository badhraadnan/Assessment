package com.assessment.customerCRUD.controller;

import com.assessment.customerCRUD.dto.customerDto;
import com.assessment.customerCRUD.entity.Customer;
import com.assessment.customerCRUD.service.customerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customer")
public class CustomerController {

    @Autowired
    private customerService customerService;

    @PostMapping("/add")
    public ResponseEntity<customerDto> addCustomer(@RequestBody customerDto customerDto){
        return customerService.saveCustomer(customerDto);
    }

    @GetMapping("/all-customer")
    public ResponseEntity<List<customerDto>> getAllCustomer(){
        return customerService.getCustomer();
    }

    @PutMapping("/update-customer")
    public ResponseEntity<customerDto> updateCustomer(@RequestBody customerDto customerDto){
        return customerService.updateCustomer(customerDto);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<customerDto> getCustomerById(@PathVariable String customerId){
        return customerService.customerFindById(customerId);
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable String customerId){
        return customerService.deleteCustomer(customerId);
    }
}
