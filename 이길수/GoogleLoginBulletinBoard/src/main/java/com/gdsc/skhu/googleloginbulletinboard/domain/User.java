package com.gdsc.skhu.googleloginbulletinboard.domain;

import com.gdsc.skhu.googleloginbulletinboard.dto.PostRequest;
import com.gdsc.skhu.googleloginbulletinboard.dto.UserInfo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USER_EMAIL", nullable = false)
    private String email;

    @Column(name = "USER_NAME", nullable = false)
    private String name;

    @Column(name = "USER_PICTURE_URL", nullable = false)
    private String pictureUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "USER_ROLE", nullable = false)
    private Role role;

    @OneToMany(mappedBy = "user")
    @Column(name = "USER_POSTS")
    private List<Post> posts = new ArrayList<>();
}