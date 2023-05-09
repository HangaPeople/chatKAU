package com.chatkau.security.token.dto.properties;

import io.jsonwebtoken.SignatureAlgorithm;

import java.security.Key;

import static io.jsonwebtoken.security.Keys.secretKeyFor;

public interface JWT {
    String TOKEN_PREFIX = "Bearer ";
    String ACCESS_TOKEN_HEADER = "Authorization";
    String REFRESH_TOKEN_HEADER = "Refresh-token";

    Key SECRET_KEY = secretKeyFor(SignatureAlgorithm.HS256);
    Key REFRESH_SECRET_KEY = secretKeyFor(SignatureAlgorithm.HS256);
}
