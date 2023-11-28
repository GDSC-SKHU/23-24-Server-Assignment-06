package com.gdsc.noticeservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
public class PostDto {
    private Long id;
    // 게시글의 제목
    private String title;
    // 게시글의 내용
    private String content;
    // 작성자의 이름
    private String author;
}
