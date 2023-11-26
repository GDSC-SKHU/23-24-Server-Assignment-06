package com.gdsc.oauth2example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@AllArgsConstructor
public class PostResponseDto {
    private String Author;
    private String title;
    private String content;
}
