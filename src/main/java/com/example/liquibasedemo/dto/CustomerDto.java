package com.example.liquibasedemo.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
public class CustomerDto {

    private String name;
    private Long rating;

    public CustomerDto(String name, Long rating) {
        this.name = name;
        this.rating = rating;
    }
}
