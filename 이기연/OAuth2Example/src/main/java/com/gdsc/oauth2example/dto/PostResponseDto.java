package com.gdsc.oauth2example.dto;

import lombok.*;

@Data
@Builder
public class PostResponseDto {

    private String userName;
    private String title;
    private String body;

}
