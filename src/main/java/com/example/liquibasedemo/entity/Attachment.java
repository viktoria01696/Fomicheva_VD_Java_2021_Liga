package com.example.liquibasedemo.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Attachment {

    private Long id;
    private Message message;
    private Post post;
    @NonNull
    private String url;
    @NonNull
    private String type;

}
