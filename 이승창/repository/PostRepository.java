package com.gdsc.noticeservice.repository;

import com.gdsc.noticeservice.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findPostByTitle(String title);
}
