package com.example.liquibasedemo.entity;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Message {

    private Long id;
    @NonNull
    private Student student;
    @NonNull
    private Chat chat;
    private String messageBody;
    @NonNull
    private Date createDate;
    private Message parentMessage;
    private Boolean isRead;
    private Attachment attachment;

}
