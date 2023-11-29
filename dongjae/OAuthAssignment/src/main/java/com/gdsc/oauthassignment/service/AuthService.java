package com.gdsc.oauthassignment.service;


import com.gdsc.oauthassignment.config.jwt.JwtService;
import com.gdsc.oauthassignment.controller.dto.response.auth.SignInResponseDto;
import com.gdsc.oauthassignment.controller.dto.response.auth.TokenResponseDto;
import com.gdsc.oauthassignment.domain.user.SocialType;
import com.gdsc.oauthassignment.domain.user.User;
import com.gdsc.oauthassignment.exception.ErrorCode;
import com.gdsc.oauthassignment.exception.model.NotFoundException;
import com.gdsc.oauthassignment.oauth2.userInfo.OAuth2UserInfo;
import com.gdsc.oauthassignment.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtService jwtService;

    private final Duration TOKEN_EXPIRATION_TIME_ACCESS = Duration.ofDays(100); // 100일
    private final Duration TOKEN_EXPIRATION_TIME_REFRESH = Duration.ofDays(200); // 200일

    private final UserRepository userRepository;
    public final RestTemplate restTemplate;

    @Transactional
    public SignInResponseDto signIn(OAuth2UserInfo oAuth2UserInfo, String provider) {
        SocialType socialType = SocialType.valueOf(provider.toUpperCase());
        String socialId = oAuth2UserInfo.getId();
        String userName = oAuth2UserInfo.getName();
        Boolean isRegistered = userRepository.existsBySocialIdAndSocialType(socialId, socialType);

        if (!isRegistered) {
            User newUser = User.builder()
                    .userName(userName)
                    .socialId(socialId)
                    .socialType(socialType)
                    .build();
            User user = userRepository.save(newUser);
            System.out.println(user.getUserName());
        }

        User user = userRepository.findBySocialIdAndSocialType(socialId, socialType)
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_USER_EXCEPTION, ErrorCode.NOT_FOUND_USER_EXCEPTION.getMessage()));

        String accessToken = jwtService.issuedToken(String.valueOf(user.getId()), TOKEN_EXPIRATION_TIME_ACCESS.toMillis());
        String refreshToken = jwtService.issuedToken(String.valueOf(user.getId()), TOKEN_EXPIRATION_TIME_REFRESH.toMillis());

        user.updateRefreshToken(refreshToken);
        userRepository.save(user);

        return SignInResponseDto.of(user.getId(), user.getUserName(), accessToken, refreshToken, isRegistered);
    }

    @Transactional
    public TokenResponseDto issueToken(String refreshToken) {
        jwtService.verifyToken(refreshToken);

        User user = userRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_USER_EXCEPTION, ErrorCode.NOT_FOUND_USER_EXCEPTION.getMessage()));

        String newAccessToken = jwtService.issuedToken(String.valueOf(user.getId()), TOKEN_EXPIRATION_TIME_ACCESS.toMillis());
        String newRefreshToken = jwtService.issuedToken(String.valueOf(user.getId()), TOKEN_EXPIRATION_TIME_REFRESH.toMillis());

        user.updateRefreshToken(newRefreshToken);
        userRepository.save(user);
        return TokenResponseDto.of(newAccessToken, newRefreshToken);
    }

    @Transactional
    public void signOut(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_USER_EXCEPTION, ErrorCode.NOT_FOUND_USER_EXCEPTION.getMessage()));
        user.updateRefreshToken(null);
    }

}
