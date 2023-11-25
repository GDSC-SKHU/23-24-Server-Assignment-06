package com.gdsc.googleloginexample.repository;

import com.gdsc.googleloginexample.domain.Post;
import com.gdsc.googleloginexample.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    boolean existsByPostSubject(String postSubject); // 쓸 데 없는 기능인거 알지만 그냥 연습차원에서 만들어 봤습니다(같은 제목의 글은 못쓰게끔)

    List<Post> findByUser(User user);
}
