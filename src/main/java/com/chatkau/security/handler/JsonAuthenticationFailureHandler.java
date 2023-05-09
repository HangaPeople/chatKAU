package com.chatkau.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;

public class JsonAuthenticationFailureHandler implements AuthenticationFailureHandler {
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        String errMsg;
        if(exception instanceof UsernameNotFoundException) {
            errMsg = "Invalid Id or Password";
        }
        else if(exception instanceof BadCredentialsException) {
            errMsg = "Invalid Id or Password";
        }
        else {
            System.out.println(exception.getClass().getName());
            errMsg = "Unknown Exception";
        }

        mapper.writeValue(response.getWriter(), errMsg);
    }
}
