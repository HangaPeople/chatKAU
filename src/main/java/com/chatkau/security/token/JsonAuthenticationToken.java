package com.chatkau.security.token;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JsonAuthenticationToken extends AbstractAuthenticationToken {
    private final Object principal;
    private final Object credentials;

    // 인증 이전 정보
    public JsonAuthenticationToken(Object principal, Object credentials) {
        super(null);
        this.principal = principal;
        this.credentials = credentials;
        this.setAuthentication(false);
    }

    // 인증 이후 정보(그래서 권한 정보 담는 authorities 존재)
    public JsonAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
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

    public void setAuthentication(boolean isAuthenticated) throws IllegalArgumentException {
        // 이거 호출하는 시점 = 권한 받기 전
        // 근데 권한을 이미 받은 상태다? = 해커
        if(isAuthenticated) {
            throw new IllegalArgumentException("Illegal authentication.");
        } else {
            super.setAuthenticated(false);
        }
    }
}
