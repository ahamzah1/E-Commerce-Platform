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
public class CustomerRequest {
    private String id;
    @NotNull(message = "Customer First name is required")
    private String firstname;
    @NotNull(message = "Customer Last name is required")
    private String lastname;
    @NotNull(message = "Customer Email is required")
    @Email(message = "Not valid email address")
    private String email;
    @NotNull( message = "Customer address is needed")
    private Address address;

    public CustomerRequest(Customer customer) {
        this.id = customer.getId();
        this.firstname = customer.getFirstname();
        this.lastname = customer.getLastname();
        this.address = customer.getAddress();
        this.email = customer.getEmail();
    }
}
