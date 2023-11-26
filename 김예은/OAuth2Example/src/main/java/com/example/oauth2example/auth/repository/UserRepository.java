package com.example.oauth2example.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.oauth2example.auth.domain.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String userEmail);
}