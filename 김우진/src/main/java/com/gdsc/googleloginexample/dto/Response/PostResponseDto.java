package com.gdsc.googleloginexample.dto.Response;

import com.gdsc.googleloginexample.domain.Post;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PostResponseDto {

    private Long postId;
    private String postSubject;
    private String postDetail;

    public static PostResponseDto of(Long postId, String postSubject, String postDetail) {
        return new PostResponseDto(postId, postSubject, postDetail);
    }
}
