package com.example.oauth2example.auth.dto;
import com.example.oauth2example.auth.domain.User;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PostRequest {

    private Long postId;
    private String author;
    private String name;
    private String content;
    private User user;

}