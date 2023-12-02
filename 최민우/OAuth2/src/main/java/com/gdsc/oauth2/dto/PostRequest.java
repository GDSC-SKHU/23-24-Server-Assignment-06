package com.gdsc.oauth2.dto;

import com.gdsc.oauth2.domain.User;
import lombok.*;

@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PostRequest {
    private Long id;
    private String title;
    private String content;
    private String writer;
    private User user;
}