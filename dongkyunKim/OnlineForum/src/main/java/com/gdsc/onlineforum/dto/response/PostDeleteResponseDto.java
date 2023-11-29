package com.gdsc.onlineforum.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostDeleteResponseDto {

    Long PostId;

    public static PostDeleteResponseDto of(Long PostId) {
        return new PostDeleteResponseDto(PostId);
    }
}
