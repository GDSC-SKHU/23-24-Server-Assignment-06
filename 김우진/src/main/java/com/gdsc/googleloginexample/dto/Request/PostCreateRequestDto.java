package com.gdsc.googleloginexample.dto.Request;

import com.gdsc.googleloginexample.domain.Post;
import com.gdsc.googleloginexample.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostCreateRequestDto {

    private String postSubject;
    private String postDetail;

    public Post toDto(User user) {
        return Post.builder()
                .postSubject(postSubject)
                .postDetail(postDetail)
                .user(user)
                .build();
    }
}
