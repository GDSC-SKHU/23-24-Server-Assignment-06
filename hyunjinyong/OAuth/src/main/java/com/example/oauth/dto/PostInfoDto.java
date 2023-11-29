package com.example.oauth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostInfoDto {
    private Long postId;
    private String title;
    private String content;
    private String writer;

    public static PostInfoDto makeOf(Long postId, String title, String content, String writer){
        return new PostInfoDto(postId, title, content, writer);
    }
}
