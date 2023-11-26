package com.gdsc.oauthassignment.controller.dto.response.post;

import com.gdsc.oauthassignment.domain.post.Post;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostResponseDto {
    private Long userId;
    private Long ornamentId;
    private String title;
    private String content;

    public static PostResponseDto of(Post post){
        return PostResponseDto.builder()
                .userId(post.getId())
                .title(post.getTitle())
                .ornamentId(post.getOrnamentId())
                .content(post.getContent())
                .build();
    }

}
