package com.gdsc.oauth2example.dto;

import com.gdsc.oauth2example.domain.Post;

public record PostResponse (
        Long id,
        String content,
        String username
){
    public static PostResponse createInstance(Post post) {
        return new PostResponse(post.getId(), post.getContent(), post.getUser().getName()
        );
    }
}
