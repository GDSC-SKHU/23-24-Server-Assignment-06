package com.gdsc.oauth2assignment.jwt;

import com.gdsc.oauth2assignment.domain.User;
import com.gdsc.oauth2assignment.dto.Token;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

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

    public Authentication getAuthentication(String accessToken) {
        Claims claims = parseClaims(accessToken);

        if (claims.get("auth") == null) {
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }
        // 위 과정을 통과하면 권한 정보가 있는 토큰

        Collection<? extends GrantedAuthority> authorities = Arrays.stream(claims.get("auth").toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList()); // 쉼표로 권한이 분리되는데, 권한이 ROLE_USER 밖에 없어서 하나밖에 없을 것으로 예상

        return new UsernamePasswordAuthenticationToken(claims.getSubject(), "", authorities); // subject, 비밀번호, 권한. 비밀번호 사용 안하니까 빈 문자열로
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization"); // HTTP 요청 헤더의 Authorization 파라미터의 값을 읽어들이고, bearerToken 변수에 저장(Bearer를 접두어로)

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) { // Bearer가 있는지와 Bearer로 시작하는지 확인
            return bearerToken.substring(7); // Bearer를 제외하고 반환
        }

        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);

            return true;
        } catch (UnsupportedJwtException | ExpiredJwtException | IllegalArgumentException e) {
            return false;
        }
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
