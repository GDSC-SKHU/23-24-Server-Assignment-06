package com.gdsc.oauth2example.dto;

import com.gdsc.oauth2example.domain.Post;
import com.gdsc.oauth2example.domain.User;
import lombok.*;


@Getter
@ToString
@NoArgsConstructor
public class PostResponseDto {
    private Long id;
    private String title;
    private String content;
    private String author;

    public Post toEntity(){
        return Post.builder()
                .id(id)
                .title(title)
                .content(content)
                .build();
    }
    @Builder
    public PostResponseDto(Long id, String title, String content, String author){
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
