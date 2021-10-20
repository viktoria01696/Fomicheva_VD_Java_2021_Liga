package com.example.liquibasedemo.mapper;

import com.example.liquibasedemo.dto.CustomerDto;
import com.example.liquibasedemo.entity.Customer;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
public class CustomerMapper {

    public CustomerDto toDto(Customer customer){
        return new CustomerDto(customer.getName(), customer.getRating());
    }

    public Customer toEntity(CustomerDto customerDto){
        Customer customer = new Customer();
        customer.setId(UUID.randomUUID());
        customer.setName(customerDto.getName());
        customer.setRating(customerDto.getRating());
        return customer;
    }
}
