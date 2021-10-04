package com.example.liquibasedemo.entity;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Post {

    private Long id;
    @NonNull
    private Student student;
    private String postBody;
    @NonNull
    private Date createDate;
    private Attachment attachment;

}
