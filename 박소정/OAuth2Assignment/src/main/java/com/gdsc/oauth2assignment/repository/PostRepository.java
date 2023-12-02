package com.gdsc.oauth2assignment.repository;

import com.gdsc.oauth2assignment.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
