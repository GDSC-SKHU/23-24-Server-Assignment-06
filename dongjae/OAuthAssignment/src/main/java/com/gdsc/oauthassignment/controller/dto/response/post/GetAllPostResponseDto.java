package com.gdsc.oauthassignment.controller.dto.response.post;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetAllPostResponseDto {

    private List<PostResponseDto> postResponseDtos;

    public static GetAllPostResponseDto of(List<PostResponseDto> postResponseDtos) {
        return new GetAllPostResponseDto(postResponseDtos);
    }

}
