package com.ahmad.ecommerce.Controllers;

import com.ahmad.ecommerce.DTO.CustomerRequest;
import com.ahmad.ecommerce.DTO.CustomerResponse;
import com.ahmad.ecommerce.Services.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/customers")
@RequiredArgsConstructor
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<String> createCustomer(@Valid @RequestBody CustomerRequest request){
        return ResponseEntity.ok(customerService.handleAdd(request));
    }

    @PutMapping
    public ResponseEntity<Void> updateCustomer(@Valid @RequestBody CustomerRequest request){
        customerService.handleEdit(request);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> findAll(){
        return ResponseEntity.ok(customerService.findAll());
    }

    @GetMapping("/exits/{id}")
    public ResponseEntity<Boolean> existsById(@PathVariable String id){
        return ResponseEntity.ok(customerService.checkId(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> findById(@PathVariable String id){
        return ResponseEntity.ok(customerService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        customerService.delete(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
    }
}
