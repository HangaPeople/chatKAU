package com.chatkau.security.filter;

import com.chatkau.security.token.JwtAuthenticationToken;
import com.chatkau.security.token.dto.properties.JWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final AuthenticationManager authenticationManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(JWT.ACCESS_TOKEN_HEADER);

        if(StringUtils.hasText(token) && token.startsWith(JWT.TOKEN_PREFIX)) {
            try {
                Authentication jwtAuthenticationToken = new JwtAuthenticationToken(token.substring(JWT.TOKEN_PREFIX.length()));
                Authentication authentication = authenticationManager.authenticate(jwtAuthenticationToken);

                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch(AuthenticationException e) {
                SecurityContextHolder.clearContext();
            }
        }

        try {
            filterChain.doFilter(request, response);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
