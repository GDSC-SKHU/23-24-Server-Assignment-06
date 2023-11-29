package com.gdsc.oauth2example.dto;

import lombok.Data;

@Data
public class PostRequestDto {

    private Long userId;
    private String title;
    private String body;

}
