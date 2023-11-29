package com.gdsc.googleloginexample.jwt;

import com.gdsc.googleloginexample.domain.User;
import com.gdsc.googleloginexample.dto.Token;
import com.gdsc.googleloginexample.exception.ErrorCode;
import com.gdsc.googleloginexample.exception.model.CustomException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class TokenProvider {

    private final Key key;

    private final long accessTokenValidityTime;

    public TokenProvider(@Value("${jwt.secret}") String secretKey,
                         @Value("${jwt.access-token-validity-in-milliseconds}") long accessTokenValidityTime) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.accessTokenValidityTime = accessTokenValidityTime;
    }

    public Token createToken(User user) {
        long nowTime = (new Date()).getTime(); // 현재 시각

        Date tokenExpiredTime = new Date(nowTime + accessTokenValidityTime);

        String accessToken = Jwts.builder()
                .setSubject(user.getId().toString())
                .claim("auth", user.getRole().name())
                .setExpiration(tokenExpiredTime)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return Token.builder()
                .accessToken(accessToken)
                .build();
    }

    public Authentication getAuthentication(String accessToken) { // access토큰을 통해서 인증 정보를 반환하는 메서드
        Claims claims = parseClaims(accessToken);

        if (claims.get("auth") == null) {
            throw new CustomException(ErrorCode.FORBIDDEN_AUTH_EXCEPTION, ErrorCode.FORBIDDEN_AUTH_EXCEPTION.getMessage());
        }

        Collection<? extends GrantedAuthority> authorities = Arrays.stream(claims.get("auth").toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return new UsernamePasswordAuthenticationToken(claims.getSubject(),"", authorities);
    }

    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        // 코드 흐름에 대해서 잘 생각해보고 예외처리를 추가하기 명심하고 또 명심할것..
        return null;
    }

    public boolean validateToken(String token) { // 토큰 검증
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (UnsupportedJwtException | ExpiredJwtException | IllegalArgumentException e) {
            // UnsupportedJwtException => Jwt 해독할 수 없는 경우에 발생(파싱하는데 알고리즘이나 포맷이 지원되지 않을 때)
            // ExpiredJwtException => Jwt가 만료되었을때 발생하는 토큰
            // IllegalArgumentException => 잘못된 인자(null, 빈 문자열) 전달되었을 때 발생되는 예외처리
            return false;
        }
    }
}
