package com.example.oauth2example.auth.dto;
import com.example.oauth2example.auth.domain.User;
import com.example.oauth2example.auth.domain.Post;
import lombok.*;
@Getter
@Setter
public class PostResponse {
    private Long postId;
    private String author;
    private String name;
    private String content;
    private Long userId;

    @Builder
    public PostResponse(Long postId, String author, String name, String content, Long userId ) {
        this.postId = postId;
        this.author = author;
        this.name = name;
        this.content =content;
        this.userId = userId;
    }

}
