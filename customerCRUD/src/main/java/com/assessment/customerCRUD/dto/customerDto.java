package com.assessment.customerCRUD.dto;

import com.assessment.customerCRUD.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class customerDto {

    private String customerId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String emailAddress;
    private String phoneNumber;

    public static Customer convertToEntity(customerDto dto) {
        return Customer.builder()
                .firstName(dto.getFirstName())
                .middleName(dto.getMiddleName())
                .lastName(dto.getLastName())
                .emailAddress(dto.getEmailAddress())
                .phoneNumber(dto.getPhoneNumber())
                .build();
    }

    public static customerDto convertToDto(Customer customer) {
        return customerDto.builder()
                .customerId(customer.getCustomerId())
                .firstName(customer.getFirstName())
                .middleName(customer.getMiddleName())
                .lastName(customer.getLastName())
                .emailAddress(customer.getEmailAddress())
                .phoneNumber(customer.getPhoneNumber())
                .build();
    }


    public static void updateCustomer(customerDto dto,Customer customer){
        if (dto.getFirstName() != null){
            customer.setFirstName(customer.getFirstName());
        }
        if (dto.getMiddleName() != null){
            customer.setMiddleName(dto.getMiddleName());
        }
        if (dto.getLastName() != null){
            customer.setLastName(dto.getLastName());
        }
        if (dto.getEmailAddress() != null){
            customer.setEmailAddress(dto.getEmailAddress());
        }
        if (dto.getPhoneNumber() != null){
            customer.setPhoneNumber(dto.getPhoneNumber());
        }
    }
}
