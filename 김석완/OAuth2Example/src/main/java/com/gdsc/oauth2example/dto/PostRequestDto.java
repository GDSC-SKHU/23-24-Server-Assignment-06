package com.gdsc.oauth2example.dto;

import com.gdsc.oauth2example.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Getter
@ToString
@NoArgsConstructor
public class PostRequestDto {

    private User user;
    private String title;
    private String content;
}
