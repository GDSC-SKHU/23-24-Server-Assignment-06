package com.gdsc.oauth2example.dto;

import com.gdsc.oauth2example.domain.Post;
import lombok.Data;


@Data
public class PostRequestDto {

    private Long userId;
    private String title;
    private String content;
}
