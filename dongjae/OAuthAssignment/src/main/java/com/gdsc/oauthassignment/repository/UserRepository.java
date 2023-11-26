package com.gdsc.oauthassignment.repository;

import com.gdsc.oauthassignment.domain.user.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Boolean existsBySocialIdAndSocialType(String socialId, SocialType socialType);
    Optional<User> findBySocialIdAndSocialType(String socialId, SocialType socialType);
    Optional<User> findByRefreshToken(String refreshToken);

}
