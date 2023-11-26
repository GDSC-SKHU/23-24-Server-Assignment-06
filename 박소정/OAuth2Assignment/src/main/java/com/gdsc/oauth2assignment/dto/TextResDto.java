package com.gdsc.oauth2assignment.dto;

import com.gdsc.oauth2assignment.domain.Text;
import lombok.Builder;
import lombok.Getter;

@Getter
public class TextResDto {
    private final Long id;
    private final String title;
    private final String content;
    private final String writer;

    @Builder
    public TextResDto(Text text) {
        this.id = text.getId();
        this.title = text.getTitle();
        this.content = text.getContent();
        this.writer = text.getUser().getName();
    }
}
