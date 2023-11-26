package com.gdsc.oauth2example.repository;

import com.gdsc.oauth2example.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
