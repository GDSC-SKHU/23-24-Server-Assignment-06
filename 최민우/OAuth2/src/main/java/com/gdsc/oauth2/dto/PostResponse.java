package com.gdsc.oauth2.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostResponse {
    private String title;
    private String content;
    private String writer;
}