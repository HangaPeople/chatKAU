package com.chatkau.security.token;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {
    private Object principal;
    private Object credentials;
    private String jwt;

    public JwtAuthenticationToken(String jwt) {
        super(null);
        this.setAuthenticated(false);
        this.jwt = jwt;
    }

    public JwtAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if(isAuthenticated) {
            throw new IllegalArgumentException("Illegal Authentication.");
        }
        else {
            super.setAuthenticated(false);
        }
    }

    public String getJwt() {
        return jwt;
    }
}
