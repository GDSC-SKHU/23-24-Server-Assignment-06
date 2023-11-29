package com.example.oauth.dto;

import com.example.oauth.domain.Post;
import jakarta.validation.constraints.NotBlank;


public record PostSaveDto(@NotBlank(message = "제목입력") String title,
                          @NotBlank(message = "내용입력") String content) {
    public Post toEntity() {
        return Post.builder().title(title).content(content).build();
    }
}