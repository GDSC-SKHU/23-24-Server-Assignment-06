package com.gdsc.oauth2assignment.service;

import com.gdsc.oauth2assignment.domain.Role;
import com.gdsc.oauth2assignment.domain.User;
import com.gdsc.oauth2assignment.dto.Token;
import com.gdsc.oauth2assignment.dto.UserInfo;
import com.gdsc.oauth2assignment.jwt.TokenProvider;
import com.gdsc.oauth2assignment.repository.UserRepository;
import com.google.gson.Gson;
import java.net.URI;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthService {

    private final String googleClientId;
    private final String googleClientSecret;
    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;
    private final String GOOGLE_TOKEN_URL = "https://oauth2.googleapis.com/token";
    private final String GOOGLE_REDIRECT_URI = "http://localhost:8080/login/oauth2/code/google";
    private final String GOOGLE_REDIRECT_URI_ADMIN = "http://localhost:8080/login/oauth2/code/google/admin";

    public AuthService(@Value("${spring.security.oauth2.client.registration.google.client-id}") String googleClientId,
                       @Value("${spring.security.oauth2.client.registration.google.client-secret}") String googleClientSecret,
                       UserRepository userRepository,
                       TokenProvider tokenProvider) {
        this.googleClientId = googleClientId;
        this.googleClientSecret = googleClientSecret;
        this.userRepository = userRepository;
        this.tokenProvider = tokenProvider;
    }

    public String getGoogleAccessToken(String code) {
        RestTemplate restTemplate = new RestTemplate(); //  HTTP 요청을 보내기 위한 RestTemplate 객체 생성
        Map<String, String> params = Map.of(
                "code", code,
                "scope",
                "https://www.googleapis.com/auth/userinfo.profile https://www.googleapis.com/auth/userinfo.email",
                "client_id", googleClientId,
                "client_secret", googleClientSecret,
                "redirect_uri", GOOGLE_REDIRECT_URI,
                "grant_type", "authorization_code"
        );

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(GOOGLE_TOKEN_URL, params, String.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            String json = responseEntity.getBody();
            Gson gson = new Gson();

            return gson.fromJson(json, Token.class)
                    .getAccessToken(); // 성공인 경우, 응답 바디에서 파싱하여 액세스 토큰 반환
        }

        throw new RuntimeException("구글 엑세스 토큰을 가져오는데 실패했습니다.");
    }

    public String getGoogleAdminAccessToken(String code) {
        RestTemplate restTemplate = new RestTemplate(); //  HTTP 요청을 보내기 위한 RestTemplate 객체 생성
        Map<String, String> params = Map.of(
                "code", code,
                "scope",
                "https://www.googleapis.com/auth/userinfo.profile https://www.googleapis.com/auth/userinfo.email",
                "client_id", googleClientId,
                "client_secret", googleClientSecret,
                "redirect_uri", GOOGLE_REDIRECT_URI_ADMIN,
                "grant_type", "authorization_code"
        );

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(GOOGLE_TOKEN_URL, params, String.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            String json = responseEntity.getBody();
            Gson gson = new Gson();

            return gson.fromJson(json, Token.class)
                    .getAccessToken(); // 성공인 경우, 응답 바디에서 파싱하여 액세스 토큰 반환
        }

        throw new RuntimeException("구글 엑세스 토큰을 가져오는데 실패했습니다.");
    }

    public Token loginOrSignUp(String googleAccessToken) {
        UserInfo userInfo = getUserInfo(googleAccessToken);

        if (!userInfo.getVerifiedEmail()) {
            throw new RuntimeException("이메일 인증이 되지 않은 유저입니다.");
        }

        User user = userRepository.findByEmail(userInfo.getEmail()).orElseGet(() ->
                userRepository.save(User.builder()
                        .email(userInfo.getEmail())
                        .name(userInfo.getName())
                        .role(Role.ROLE_USER)
                        .build()) // 등록되어 있지 않으면 새롭게 생성하고 정보 가져옴
        );

        return tokenProvider.createToken(user);
    }

    public Token adminLoginOrSignUp(String googleAccessToken) {
        UserInfo userInfo = getUserInfo(googleAccessToken);

        if (!userInfo.getVerifiedEmail()) {
            throw new RuntimeException("이메일 인증이 되지 않은 관리자입니다.");
        }

        User user = userRepository.findByEmail(userInfo.getEmail()).orElseGet(() ->
                userRepository.save(User.builder()
                        .email(userInfo.getEmail())
                        .name(userInfo.getName())
                        .role(Role.ROLE_ADMIN)
                        .build()) // 등록되어 있지 않으면 새롭게 생성하고 정보 가져옴
        );

        return tokenProvider.createToken(user);
    }


    public UserInfo getUserInfo(String accessToken) {
        RestTemplate restTemplate = new RestTemplate();
        String url =
                "https://www.googleapis.com/oauth2/v2/userinfo?access_token=" + accessToken; // 사용자 정보를 가져오기 위한 URL 설정

        HttpHeaders headers = new HttpHeaders(); // HTTP 요청의 헤더를 설정하는 객체
        headers.set("Authorization", "Bearer " + accessToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        RequestEntity<Void> requestEntity = new RequestEntity<>(
                headers,
                HttpMethod.GET,
                URI.create(url)
        ); // HTTP GET 요청 객체
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class); // HTTP 요청을 보내고, 그 응답을 ResponseEntity 객체에 저장

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            String json = responseEntity.getBody();
            Gson gson = new Gson();
            return gson.fromJson(json, UserInfo.class);
        }

        throw new RuntimeException("유저 정보를 가져오는데 실패했습니다.");
    }
}
