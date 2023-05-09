package com.chatkau.security.token;

import com.chatkau.entity.UserLogin;
import com.chatkau.security.token.dto.properties.JWT;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtFactory {
    private final int SECOND = 1000;
    private final int MINUTE = SECOND * 60;

    private final String issuer;
    private final long expirePeriod;
    private final long refreshExpirePeriod;

    public JwtFactory(
            @Value("${jwt.issuer}") String issuer,
                      @Value("${jwt.expire-period}") long expirePeriod,
                      @Value("${jwt.refresh-expire-period}") long refreshExpirePeriod
    ) {
        this.issuer = issuer;
        this.expirePeriod = expirePeriod;
        this.refreshExpirePeriod = refreshExpirePeriod;
    }

    public String createAccessToken(UserLogin userLogin) {
        return createToken(userLogin, JWT.SECRET_KEY, expirePeriod);
    }

    public String createRefreshToken(UserLogin userLogin) {
        return createToken(userLogin, JWT.REFRESH_SECRET_KEY, refreshExpirePeriod);
    }

    private String createToken(UserLogin userLogin, Key secretKey, long expirePeriod) {
        Claims claims = Jwts.claims().setSubject(userLogin.getUsername());
        claims.put("roles", userLogin.getRole());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuer(issuer)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirePeriod * MINUTE))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }
}
