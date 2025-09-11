package com.assessment.customerCRUD.service.IMPL;

import com.assessment.customerCRUD.dto.customerDto;
import com.assessment.customerCRUD.entity.Customer;
import com.assessment.customerCRUD.exception.CustomerNotFound;
import com.assessment.customerCRUD.repo.customerRepo;
import com.assessment.customerCRUD.service.customerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class customerIMPL implements customerService {
    @Autowired
    private customerRepo customerRepo;


    @Override
    public ResponseEntity<customerDto> saveCustomer(customerDto customerDto) {
        Optional<Customer> existCustomer = customerRepo.findByEmailAddress(customerDto.getEmailAddress());

        if (existCustomer.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .build();
        }

        Customer customer = customerDto.convertToEntity(customerDto);
        Customer savedCustomer = customerRepo.save(customer);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(customerDto.convertToDto(savedCustomer));
    }

    @Override
    public ResponseEntity<List<customerDto>> getCustomer() {
        List<Customer> customers = customerRepo.findAll();

            List<customerDto> dto = customers.stream()
                    .map(customerDto::convertToDto)
                    .toList();

            return ResponseEntity
                    .ok(dto);


    }

    @Override
    public ResponseEntity<customerDto> updateCustomer(customerDto dto) {
        Customer existCustomer = customerRepo.findByCustomerId(dto.getCustomerId())
                .orElseThrow(() -> new CustomerNotFound("Customer Not Exist"));;


            customerDto.updateCustomer(dto, existCustomer);

            Customer saveCustomer = customerRepo.save(existCustomer);
            return ResponseEntity
                    .ok(customerDto.convertToDto(saveCustomer));




    }

    @Override
    public ResponseEntity<customerDto> customerFindById(String customerId) {
        Customer existCustomer = customerRepo.findByCustomerId(customerId)
                .orElseThrow(() -> new CustomerNotFound("Customer Not Exist"));;


        return ResponseEntity.ok(customerDto.convertToDto(existCustomer));
    }

    @Override
    public ResponseEntity<Void> deleteCustomer(String customerId) {
        Customer customer = customerRepo.findByCustomerId(customerId)
                .orElseThrow(() -> new CustomerNotFound("Customer Not Exist"));

        customerRepo.delete(customer);
        return ResponseEntity.ok().build();
    }




}
