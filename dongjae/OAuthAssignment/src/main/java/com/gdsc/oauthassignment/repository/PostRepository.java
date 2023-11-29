package com.gdsc.oauthassignment.repository;

import com.gdsc.oauthassignment.domain.post.Post;
import com.gdsc.oauthassignment.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository <Post, Integer> {

    List<Post> findAllByUser(User user);

}
