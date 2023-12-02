package com.gdsc.oauth2assignment.dto;

import com.gdsc.oauth2assignment.domain.Post;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostResDto {
    private final Long id;
    private final String title;
    private final String content;
    private final String writer;

    @Builder
    public PostResDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.writer = post.getUser().getName();
    }
}
