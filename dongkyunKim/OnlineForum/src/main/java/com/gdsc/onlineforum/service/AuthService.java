package com.gdsc.onlineforum.service;

import com.gdsc.onlineforum.domain.Role;
import com.gdsc.onlineforum.domain.User;
import com.gdsc.onlineforum.dto.TokenDto;
import com.gdsc.onlineforum.dto.UserInformationDto;
import com.gdsc.onlineforum.jwt.TokenProvider;
import com.gdsc.onlineforum.repository.UserRepository;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Map;

@Service
@RequiredArgsConstructor
@ComponentScan(basePackages={"com.gdsc.onlineforum.service"})
public class AuthService {
    private final String GOOGLE_TOKEN_URL = "https://oauth2.googleapis.com/token";
    private final String GOOGLE_REDIRECT_URI = "http://localhost:8080/api/oauth2/callback/google";

    @Value("${client_id}")
    private String GOOGLE_CLIENT_ID;

    @Value("${client_secret}")
    private String GOOGLE_CLIENT_SECRET;

    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;

    public String getGoogleAccessToken(String code) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> params = Map.of(
                "code", code,
                "scope", "https://www.googleapis.com/auth/userinfo.profile https://www.googleapis.com/auth/userinfo.email",
                "client_id", GOOGLE_CLIENT_ID,
                "client_secret", GOOGLE_CLIENT_SECRET,
                "redirect_uri", GOOGLE_REDIRECT_URI,
                "grant_type", "authorization_code"
        );

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(GOOGLE_TOKEN_URL, params, String.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            String json = responseEntity.getBody();
            Gson gson = new Gson();

            return gson.fromJson(json, TokenDto.class)
                    .getAccessToken();
        }

        throw new RuntimeException("구글 엑세스 토큰을 가져오는데 실패했습니다.");
    }

    public TokenDto loginOrSignUp(String googleAccessToken) {
        UserInformationDto userInfoDto = getUserInfo(googleAccessToken);

        if (!userInfoDto.getVerifiedEmail()) {
            throw new RuntimeException("이메일 인증이 되지 않은 유저입니다.");
        }

        User user = userRepository.findByEmail(userInfoDto.getEmail()).orElseGet(() ->
                userRepository.save(User.builder()
                        .email(userInfoDto.getEmail())
                        .name(userInfoDto.getName())
                        .pictureUrl(userInfoDto.getPictureUrl())
                        .role(Role.ROLE_USER) // 최초 가입시 USER로 설정, 원하는 대로 변경할 수 있는 응용력 필요
                        .build())
        );

        return tokenProvider.createToken(user);
    }

    public UserInformationDto getUserInfo(String accessToken) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://www.googleapis.com/oauth2/v2/userinfo?access_token=" + accessToken;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        RequestEntity<Void> requestEntity = new RequestEntity<>(headers, HttpMethod.GET, URI.create(url));
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            String json = responseEntity.getBody();
            Gson gson = new Gson();
            return gson.fromJson(json, UserInformationDto.class);
        }

        throw new RuntimeException("유저 정보를 가져오는데 실패했습니다.");
    }

}
