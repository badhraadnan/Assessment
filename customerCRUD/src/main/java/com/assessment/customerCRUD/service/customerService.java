package com.assessment.customerCRUD.service;

import com.assessment.customerCRUD.dto.customerDto;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface customerService {
    ResponseEntity<customerDto> saveCustomer(customerDto customerDto);
    ResponseEntity<List<customerDto>> getCustomer();
    ResponseEntity<customerDto> updateCustomer(customerDto dto);
    ResponseEntity<customerDto> customerFindById(String customerId);
    ResponseEntity<Void> deleteCustomer(String customerId);
}
