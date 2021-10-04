package com.example.liquibasedemo.entity;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class School {

    private Long id;
    private String name;
    @NonNull
    private String address;
    private List<Student> studentList;
}
