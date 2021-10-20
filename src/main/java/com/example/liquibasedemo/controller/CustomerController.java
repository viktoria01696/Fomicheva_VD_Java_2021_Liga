package com.example.liquibasedemo.controller;

import com.example.liquibasedemo.dto.CustomerDto;
import com.example.liquibasedemo.entity.Customer;
import com.example.liquibasedemo.persistence.CustomerRepository;
import com.example.liquibasedemo.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/crud/customer")
@RequiredArgsConstructor
@Api(value = "Customer CRUD operations", description = "Customer CRUD operations")
public class CustomerController {

    private final CustomerService customerService;

    @ApiOperation(value = "Enumerates all Customer entities")
    @GetMapping
    public List<CustomerDto> enumerate() {
        return customerService.findAllCustomer();
    }

    @ApiOperation(value = "Store given Customer entity")
    @PostMapping
    public CustomerDto save(@RequestBody CustomerDto customerDto) {
        return customerService.saveCustomer(customerDto);
    }

    @ApiOperation(value = "Retrieves Customer entity by it ID")
    @GetMapping("/{id}")
    public CustomerDto get(@PathVariable("id")String id) {
        return customerService.findCustomer(id);
    }

    @ApiOperation(value = "Create new Customer Entity")
    @PutMapping
    public CustomerDto createNewCustomer(@RequestBody CustomerDto customerDto){
        return customerService.saveCustomer(customerDto);
    }

    @ApiOperation(value = "Delete Customer")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCustomer(@PathVariable("id")String id) throws NotFoundException {
        customerService.deleteCustomer(id);
    }

    //TODO: добавить и проаннотировать операции удаления сущности Customer, и создания новой пустой сущности Customer
}
