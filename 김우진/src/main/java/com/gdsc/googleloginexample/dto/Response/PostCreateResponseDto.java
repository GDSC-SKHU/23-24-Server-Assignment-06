package com.gdsc.googleloginexample.dto.Response;

import com.gdsc.googleloginexample.domain.Post;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PostCreateResponseDto {

    private Long postId;
    private String postSubject;
    private String postDetail;
    private Long userId;
    private String userName;

    public static PostCreateResponseDto of(Post post) {
        return new PostCreateResponseDto(
                post.getPostId(),
                post.getPostSubject(),
                post.getPostDetail(),
                post.getUser().getId(),
                post.getUser().getName()
        );
    }
}
