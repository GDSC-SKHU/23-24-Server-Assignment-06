package com.gdsc.oauth2assignment.dto;

import com.gdsc.oauth2assignment.domain.Text;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TextResDto {
    private Long id;
    private String title;
    private String content;
    private String writer;

    public TextResDto(Text text) {
        this.id = text.getId();
        this.title = text.getTitle();
        this.content = text.getContent();
        this.writer = text.getUser().getName();
    }
}
