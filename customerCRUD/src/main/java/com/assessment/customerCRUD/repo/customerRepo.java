package com.assessment.customerCRUD.repo;

import com.assessment.customerCRUD.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface customerRepo extends JpaRepository<Customer,String> {

    Optional<Customer> findByEmailAddress(String email);
    Optional<Customer> findByCustomerId(String customerId);
}
