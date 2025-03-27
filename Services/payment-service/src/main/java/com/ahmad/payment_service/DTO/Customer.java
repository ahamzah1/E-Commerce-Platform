package com.ahmad.payment_service.DTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Validated
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private String id;
    @NotNull(message = "firstname is null")
    private String firstname;
    @NotNull(message = "lastname is null")
    private String lastname;
    @Email(message = "firstname is null")
    private String email;
}
