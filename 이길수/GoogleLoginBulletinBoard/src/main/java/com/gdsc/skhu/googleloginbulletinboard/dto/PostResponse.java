package com.gdsc.skhu.googleloginbulletinboard.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostResponse {
    private Long postId;
    private String title;
    private String content;
    private String writer;
    private Long userId;

    @Builder
    public PostResponse(Long postId, String title, String content, String writer, Long userId) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.userId = userId;
    }


}
