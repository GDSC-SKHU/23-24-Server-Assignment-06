package com.gdsc.oauth2example.repository;

import com.gdsc.oauth2example.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

}
