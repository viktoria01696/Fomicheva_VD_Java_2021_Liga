package com.example.liquibasedemo.service;

import com.example.liquibasedemo.dto.CustomerDto;
import com.example.liquibasedemo.entity.Customer;
import com.example.liquibasedemo.mapper.CustomerMapper;
import com.example.liquibasedemo.persistence.CustomerRepository;
import io.swagger.v3.oas.annotations.servers.Server;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public List<CustomerDto> findAllCustomer(){
        return customerRepository.findAll().stream().map(customerMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public CustomerDto saveCustomer(CustomerDto customerDto){
        customerRepository.save(customerMapper.toEntity(customerDto));
        return customerDto;
    }

    @Override
    public CustomerDto findCustomer(String id){
        Optional<Customer> customer = customerRepository.findById(UUID.fromString(id));
        return customer.map(customerMapper::toDto).orElse(null);
    }

    @Override
    public void deleteCustomer(String id) throws NotFoundException {
        Optional<Customer> customer = customerRepository.findById(UUID.fromString(id));
        Customer currentCustomer = customer.orElse(null);
        if (currentCustomer != null){
            customerRepository.deleteById(UUID.fromString(id));
        } else {
            throw new NotFoundException("Customer not found");}
    }
}
