package com.ahmad.ecommerce.DTO;

import com.ahmad.ecommerce.Entity.Address;
import com.ahmad.ecommerce.Entity.Customer;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomerResponse {
    private String id;
    private String firstname;
    private String lastname;
    private String email;
    private Address address;

    public CustomerResponse(Customer customer) {
        this.id = customer.getId();
        this.firstname = customer.getFirstname();
        this.lastname = customer.getLastname();
        this.address = customer.getAddress();
        this.email = customer.getEmail();
    }
}
