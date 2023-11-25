package com.gdsc.oauth2assignment.jwt;

import com.gdsc.oauth2assignment.dto.Token;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.util.Base64.Decoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.security.Key;
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
}
