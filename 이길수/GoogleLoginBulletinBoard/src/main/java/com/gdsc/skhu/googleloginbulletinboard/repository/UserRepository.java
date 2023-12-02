package com.gdsc.skhu.googleloginbulletinboard.repository;

import com.gdsc.skhu.googleloginbulletinboard.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String userEmail);
}
