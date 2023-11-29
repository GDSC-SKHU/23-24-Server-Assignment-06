package com.example.oauth2example.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.oauth2example.auth.domain.Post;
import com.example.oauth2example.auth.domain.User;

public interface PostRepository extends JpaRepository<Post, Long> {
}