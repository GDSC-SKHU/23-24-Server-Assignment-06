package com.gdsc.oauth2assignment.service;

import com.gdsc.oauth2assignment.dto.Token;
import com.gdsc.oauth2assignment.dto.UserInfo;
import com.gdsc.oauth2assignment.jwt.TokenProvider;
import com.gdsc.oauth2assignment.repository.UserRepository;
import com.google.gson.Gson;
import java.net.URI;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final String GOOGLE_TOKEN_URL = "https://oauth2.googleapis.com/token";
    private final String GOOGLE_REDIRECT_URI = "http://localhost:8080/login/oauth2/code/google";
    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;
    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private final String GOOGLE_CLIENT_ID;
    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    private final String GOOGLE_CLIENT_SECRET;

    public String getGoogleAccessToken(String code) {
        RestTemplate restTemplate = new RestTemplate(); //  HTTP 요청을 보내기 위한 RestTemplate 객체 생성
        Map<String, String> params = Map.of(
                "code", code,
                "scope",
                "https://www.googleapis.com/auth/userinfo.profile https://www.googleapis.com/auth/userinfo.email",
                "client_id", GOOGLE_CLIENT_ID,
                "client_secret", GOOGLE_CLIENT_SECRET,
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

    public UserInfo getUserInfo(String accessToken) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://www.googleapis.com/oauth2/v2/userinfo?access_token=" + accessToken; // 사용자 정보를 가져오기 위한 URL 설정

        HttpHeaders headers = new HttpHeaders(); // HTTP 요청의 헤더를 설정하는 객체
        headers.set("Authorization", "Bearer " + accessToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        RequestEntity<Void> requestEntity = new RequestEntity<>(headers, HttpMethod.GET, URI.create(url)); // HTTP GET 요청 객체
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class); // HTTP 요청을 보내고, 그 응답을 ResponseEntity 객체에 저장

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            String json = responseEntity.getBody();
            Gson gson = new Gson();
            return gson.fromJson(json, UserInfo.class);
        }

        throw new RuntimeException("유저 정보를 가져오는데 실패했습니다.");
    }
}
