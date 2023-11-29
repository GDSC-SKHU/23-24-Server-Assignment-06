package com.gdsc.googleloginexample.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtFilter extends GenericFilterBean {
    private final TokenProvider tokenProvider;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain Chain) throws IOException, ServletException {
        String token = tokenProvider.resolveToken((HttpServletRequest) request);

        if (StringUtils.hasText(token) && tokenProvider.validateToken(token)) { // 둘 다 true면 검증 완료
            Authentication authentication = tokenProvider.getAuthentication(token); //엑세스 토큰을 반환해서 authentication에 저장

            // security홀더에 인증 정보를 담은 객체 authentication을 넣어줌
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        Chain.doFilter(request, response); // doFilter를 다시 호출해서 다음꺼 필터링
    }
}
