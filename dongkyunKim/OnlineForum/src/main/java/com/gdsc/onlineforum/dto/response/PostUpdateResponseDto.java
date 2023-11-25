package com.gdsc.onlineforum.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostUpdateResponseDto {
    private Long PostId;
    private String title;
    private String content;
    private String author;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    public static PostUpdateResponseDto of(Long PostId, String title, String content, String author, LocalDateTime createDate, LocalDateTime updateDate) {
        return new PostUpdateResponseDto(PostId, title,  content, author, createDate, updateDate);
    }
}
