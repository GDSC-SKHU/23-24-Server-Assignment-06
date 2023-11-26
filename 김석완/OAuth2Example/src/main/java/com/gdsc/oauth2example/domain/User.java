package com.gdsc.oauth2example.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
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
    private Long userId;

    @Column(name = "USER_NAME", nullable = false)
    private String name;

    @Column(name = "USER_EMAIL", nullable = false)
    private String email;

    @Column(name = "USER_PICTURE_URL", nullable = false)
    private String pictureUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "USER_ROLE", nullable = false)
    private Role role;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();

    @Builder
    public User(Long userId, String name, String email, String pictureUrl, Role role){
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.pictureUrl = pictureUrl;
        this.role = role;
    }
}
