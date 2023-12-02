package com.gdsc.noticeservice.repository;

import com.gdsc.noticeservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByEmail(String consumerEmail);
    Optional<User> findConsumerByName(String name);
    boolean existsByEmail(String consumerEmail);
}
