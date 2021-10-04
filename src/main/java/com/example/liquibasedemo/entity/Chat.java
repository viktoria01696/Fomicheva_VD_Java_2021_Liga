package com.example.liquibasedemo.entity;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Chat {

    private Long id;
    private String name;
    @NonNull
    private Date createDate;
    @NonNull
    private Student student;
    private List<Message> messageList;
    private List<Student> studentList;
}
