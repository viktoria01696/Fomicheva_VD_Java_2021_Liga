package com.example.liquibasedemo.service;

import com.example.liquibasedemo.dto.CustomerDto;
import javassist.NotFoundException;

import java.util.List;

public interface CustomerService {

    List<CustomerDto> findAllCustomer();
    CustomerDto saveCustomer(CustomerDto customerDto);
    CustomerDto findCustomer(String id);
    void deleteCustomer(String id) throws NotFoundException;

}
