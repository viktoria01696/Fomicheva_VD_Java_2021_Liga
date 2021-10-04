package com.example.liquibasedemo.entity;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Student {

    private Long id;
    @NonNull
    private String firstname;
    @NonNull
    private String lastname;
    private String patronymic;
    @NonNull
    private Date birthday;
    @NonNull
    private Integer age;
    @NonNull
    private Integer sex;
    @NonNull
    private School school;
    private List<Post> postList;
    private List<Message> messageList;
    private List<Chat> chatList;
    private List<Student> friendList;
    private String email;
    @NonNull
    private Date createDate;
    private Boolean isActive;

}
