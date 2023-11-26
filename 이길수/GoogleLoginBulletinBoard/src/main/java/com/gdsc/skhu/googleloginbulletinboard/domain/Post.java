package com.gdsc.skhu.googleloginbulletinboard.domain;

import com.gdsc.skhu.googleloginbulletinboard.dto.PostRequest;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue
    @Column(name = "POST_ID")
    private Long postId;

    @Column(name = "POST_TITLE", length = 50, nullable = false)
    private String title;

    @Column(name = "POST_CONTENT")
    private String content;

    @Column(name = "POST_WRITER")
    private String writer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

}