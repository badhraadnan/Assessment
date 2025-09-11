package com.assessment.customerCRUD.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    @Id
    private String customerId;


    @Column(nullable = false)
    private String firstName;


    private String middleName;

    @Column( nullable = false)
    private String lastName;

    @Column( unique = true, nullable = false)
    private String emailAddress;

    private String phoneNumber;

    private LocalDateTime createdAt = LocalDateTime.now();

    @PrePersist
    public void setValues() {
        this.customerId = UUID.randomUUID().toString();
        this.createdAt = LocalDateTime.now();
    }
}
