package com.chatkau.security.provider;

import com.chatkau.security.token.JwtAuthenticationToken;
import com.chatkau.security.token.dto.properties.JWT;
import com.chatkau.security.util.UserLoginContext;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;


public class JwtAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

    // 토큰에서 인증 정보 조회
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = resolveToken((JwtAuthenticationToken) authentication);
        UserLoginContext userDetails = (UserLoginContext) userDetailsService.loadUserByUsername(username);
        return new JwtAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    public String resolveToken(JwtAuthenticationToken token) {
        return validateToken(token.getJwt());
    }

    // 토큰의 유효성과 만료일자 확인
    public String validateToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(JWT.SECRET_KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch(SignatureException exception) {
            throw new BadCredentialsException("Invalid Token Signature");
        } catch (ExpiredJwtException e) {
            throw new BadCredentialsException("Expired Token");
        } catch(MalformedJwtException e) {
            throw new BadCredentialsException("Invalid Token");
        }
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(JwtAuthenticationToken.class);
    }
}
