package com.gdsc.oauth2example.dto;

import com.gdsc.oauth2example.domain.User;
import lombok.*;

@Getter
@ToString
@NoArgsConstructor
public class PostRequestDto {
    private String author;
    private String title;
    private String content;
    private User user;
}
