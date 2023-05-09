package com.chatkau.security.handler;

import com.chatkau.entity.UserLogin;
import com.chatkau.security.token.JwtFactory;
import com.chatkau.security.token.dto.JwtDTO;
import com.chatkau.security.token.dto.properties.JWT;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

public class JsonAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private JwtFactory factory;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        UserLogin user = (UserLogin) authentication.getPrincipal();

        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        JwtDTO build = JwtDTO.builder()
                .accessToken(factory.createAccessToken(user))
                .refreshToken(factory.createRefreshToken(user))
                .build();

        response.setHeader(JWT.ACCESS_TOKEN_HEADER, JWT.TOKEN_PREFIX + build.getAccessToken());
        response.setHeader(JWT.REFRESH_TOKEN_HEADER, JWT.TOKEN_PREFIX + build.getRefreshToken());
    }
}
