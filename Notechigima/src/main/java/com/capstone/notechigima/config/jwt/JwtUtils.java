package com.capstone.notechigima.config.jwt;

import com.capstone.notechigima.config.ExceptionCode;

public class JwtUtils {
    public static final String ACCESS_TOKEN_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";

    public static String parseToken(String token) throws SecurityException {
        if (token == null) {
            throw new SecurityException(ExceptionCode.WRONG_TOKEN.getMessage());
        }

        if (token.startsWith(BEARER_PREFIX)) {
            return token.substring(BEARER_PREFIX.length());
        }
        return token;
    }
}
