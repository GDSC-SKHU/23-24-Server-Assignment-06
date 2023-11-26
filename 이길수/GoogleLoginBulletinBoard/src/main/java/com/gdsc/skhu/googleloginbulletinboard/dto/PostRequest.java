package com.gdsc.skhu.googleloginbulletinboard.dto;

import com.gdsc.skhu.googleloginbulletinboard.domain.Role;
import com.gdsc.skhu.googleloginbulletinboard.domain.User;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PostRequest {
    private Long id;
    private String title;
    private String content;
    private String writer;
    private User user;
}
