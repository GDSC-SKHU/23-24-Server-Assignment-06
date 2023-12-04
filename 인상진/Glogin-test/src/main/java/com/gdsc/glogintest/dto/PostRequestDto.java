package com.gdsc.glogintest.dto;

import com.gdsc.glogintest.domain.Post;
import lombok.Builder;
import lombok.Data;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Data
public class PostRequestDto {

    private Long userId;
    private String title;
    private String content;
    private LocalDateTime createAt;
    private LocalDateTime upeateAt;
}
