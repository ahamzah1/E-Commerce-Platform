package com.ahmad.ecommerce.Mappers;

import com.ahmad.ecommerce.DTO.CustomerRequest;
import com.ahmad.ecommerce.Entity.Customer;
import jakarta.validation.Valid;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public Customer toCustomer(CustomerRequest customer) {
        if(customer == null){
            return null;
        }
        return Customer.builder()
                .id(customer.getId())
                .firstname(customer.getFirstname())
                .lastname(customer.getLastname())
                .address(customer.getAddress())
                .email(customer.getEmail())
                .build();
    }

    public CustomerRequest toCustomerRequest(Customer customer){
        return new CustomerRequest(customer);
    }
}
