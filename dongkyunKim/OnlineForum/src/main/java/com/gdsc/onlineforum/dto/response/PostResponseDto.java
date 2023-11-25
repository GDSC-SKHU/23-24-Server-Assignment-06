package com.gdsc.onlineforum.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostResponseDto {

    private Long id;
    private String title;
    private String content;
    private String author;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    public static PostResponseDto of(Long id, String title, String content, String author, LocalDateTime createDate, LocalDateTime updateDate) {
        return new PostResponseDto(id, title, content, author, createDate, updateDate);
    }
}
