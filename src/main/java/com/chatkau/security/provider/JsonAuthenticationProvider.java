package com.chatkau.security.provider;

import com.chatkau.security.token.JsonAuthenticationToken;
import com.chatkau.security.util.UserLoginContext;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

public class JsonAuthenticationProvider implements AuthenticationProvider {
    private UserDetailsService userDetailsService;
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        UserLoginContext userLoginContext = (UserLoginContext) userDetailsService.loadUserByUsername(username);
        if(!passwordEncoder.matches(password, userLoginContext.getPassword())) {
            throw new BadCredentialsException("Invalid Password.");
        }

        userDetailsService.loadUserByUsername(username);
        return new JsonAuthenticationToken(userLoginContext.getUserLogin(), null, userLoginContext.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(JsonAuthenticationToken.class);
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
}
