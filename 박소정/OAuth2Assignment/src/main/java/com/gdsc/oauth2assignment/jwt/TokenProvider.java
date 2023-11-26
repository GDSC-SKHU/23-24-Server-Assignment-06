package com.gdsc.oauth2assignment.jwt;

import com.gdsc.oauth2assignment.domain.User;
import com.gdsc.oauth2assignment.dto.Token;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Component
public class TokenProvider {
    private final Key key; // JWT를 암호화하기 위한 비밀 키를 저장, Key는 인터페이스로 구체적인 키 값이 할당됨.
    private final long accessTokenValidityTime; // 생성된 액세스 토큰의 유효 시간 저장

    public TokenProvider(@Value("${jwt.secret}") String secretKey, // yaml 설정 파일에서 값을 가져옴
                         @Value("${ACCESS_TOKEN_VALIDITY_IN_MILLISECONDS}") long accessTokenValidityTime) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey); // 비밀키를 디코딩
        this.key = Keys.hmacShaKeyFor(keyBytes); // 디코딩한 걸, 암호화 알고리즘을 사용하는 키로 변환
        this.accessTokenValidityTime = accessTokenValidityTime;
    }

    public Token CreatToken(User user) {
        long nowTime = (new Date()).getTime();

        Date tokenExpiredTime = new Date(nowTime + accessTokenValidityTime);

        String accessToken = Jwts.builder()
                .setSubject(user.getId().toString())
                .claim("auth", user.getRole().name())
                .setExpiration(tokenExpiredTime)
                .signWith(key, SignatureAlgorithm.HS256) // 서명
                .compact(); // 액세스 토큰 생성

        return Token.builder()
                .accessToken(accessToken)
                .build(); // Token 객체로 반환
    }

    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }

        /*
         * Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
         * : 클레임 추출
         * Jwts.parserBuilder().setSigningKey(key).build() -> 파싱하는 빌더 생성, 서명 키 설정
         * parseClaimsJws(accessToken) -> 주어진 액세스 토큰 파싱
         * getBody() -> JWT에서 클레임 가져옴
         * */
    }
}
