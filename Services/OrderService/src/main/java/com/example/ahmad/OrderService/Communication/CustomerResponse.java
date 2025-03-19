package com.example.ahmad.OrderService.Communication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse {
    private String Id;

    private String firstname;

    private String lastname;

    private String email;

    private Address address;

}

