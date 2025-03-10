package com.ahmad.ecommerce.Services;

import com.ahmad.ecommerce.DTO.CustomerRequest;
import com.ahmad.ecommerce.DTO.CustomerResponse;
import com.ahmad.ecommerce.Entity.Customer;
import com.ahmad.ecommerce.Exceptions.CustomerNotFoundException;
import com.ahmad.ecommerce.Mappers.CustomerMapper;
import com.ahmad.ecommerce.Repository.CustomerRepository;
import jakarta.validation.Valid;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }


    public String handleAdd(CustomerRequest customer) {
        Customer customerToAdd = this.customerRepository.save(customerMapper.toCustomer(customer));
        return customerToAdd.getId();
    }

    public void handleEdit(CustomerRequest request) {
        Customer customer = customerRepository.findById(request.getId()).orElseThrow(() ->
                new CustomerNotFoundException(String.format("Customer not found with the provided id: %s", request.getId())));
        mergerCustomer(customer,request);
        customerRepository.save(customer);
    }


    public List<CustomerResponse> findAll() {
        return customerRepository.findAll()
                .stream()
                .map(CustomerResponse::new)
                .toList();

    }

    public Boolean checkId(String id) {
        return customerRepository.findById(id).isPresent();
    }

    public CustomerResponse findById(String id) {
        Customer res = customerRepository.findById(id).orElseThrow( () ->
                new CustomerNotFoundException(String.format("Customer not found with the provided id: %s", id)));

        return new CustomerResponse(res);
    }

    public void delete(String id) {
        Customer res = customerRepository.findById(id).orElseThrow( () ->
                new CustomerNotFoundException(String.format("Customer not found with the provided id: %s", id)));
        customerRepository.delete(res);
    }

    private void mergerCustomer(Customer customer, CustomerRequest request){
        if(StringUtils.isNotBlank(request.getFirstname())){
            customer.setFirstname(request.getFirstname());
        }
        if(StringUtils.isNotBlank(request.getLastname())){
            customer.setLastname(request.getLastname());
        }
        if(StringUtils.isNotBlank(request.getEmail())){
            customer.setEmail(request.getEmail());
        }
        if(request.getAddress() != null){
            customer.setAddress(request.getAddress());
        }
    }

}
