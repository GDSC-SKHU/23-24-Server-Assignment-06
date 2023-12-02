package com.gdsc.oauth2assignment.repository;

import com.gdsc.oauth2assignment.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String userEmail); // 이미 email을 통해 생성된 사용자인지 확인
}
