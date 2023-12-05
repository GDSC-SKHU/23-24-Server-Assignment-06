package com.gdsc.glogintest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class PostResponseDto {

    private String Author;
    private String title;
    private String content;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

}
