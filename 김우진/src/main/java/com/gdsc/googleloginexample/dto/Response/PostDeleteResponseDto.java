package com.gdsc.googleloginexample.dto.Response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PostDeleteResponseDto {

    private Long postId;

    public static PostDeleteResponseDto of(Long postId) {
        return new PostDeleteResponseDto(postId);
    }
}
