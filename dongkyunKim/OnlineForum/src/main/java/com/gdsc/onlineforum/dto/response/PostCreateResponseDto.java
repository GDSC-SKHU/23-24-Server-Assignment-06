package com.gdsc.onlineforum.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostCreateResponseDto {

    private Long PostId;
    private String title;
    private String content;
    private String author;
    private LocalDateTime createDate;


    public static PostCreateResponseDto of(Long PostId, String title, String content, String author, LocalDateTime createDate) {
        return new PostCreateResponseDto(PostId, title, content, author, createDate);
    }

}
