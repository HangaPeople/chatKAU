package com.chatkau.security.filter;

import com.chatkau.dto.user.UserLoginDTO;
import com.chatkau.security.token.JsonAuthenticationToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.ObjectUtils;

import java.io.IOException;

public class JsonLoginProcessingFilter extends AbstractAuthenticationProcessingFilter {

    private final ObjectMapper mapper = new ObjectMapper();

    public JsonLoginProcessingFilter() {
        super(new AntPathRequestMatcher("/auth/login", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        if(!isJson(request)) {
            throw new BadCredentialsException("Not a json");
        }
        UserLoginDTO dto = mapper.readValue(request.getReader(), UserLoginDTO.class);
        if(ObjectUtils.isEmpty(dto.getUsername()) || ObjectUtils.isEmpty(dto.getPassword())) {
            throw new BadCredentialsException("Invalid id or password");
        }

        JsonAuthenticationToken jsonAuthenticationToken = new JsonAuthenticationToken(dto.getUsername(), dto.getPassword());
        return getAuthenticationManager().authenticate(jsonAuthenticationToken);
    }

    private Boolean isJson(HttpServletRequest request) {
        // 요청으로 들어온 게 json인지 아닌지 판별
        return request.getHeader("Content-Type").contains("application/json");
    }
}
