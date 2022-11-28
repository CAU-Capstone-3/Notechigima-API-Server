package com.capstone.notechigima.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.capstone.notechigima.config.auth.AccountDetailService;
import com.capstone.notechigima.config.auth.AccountDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtProvider implements AuthenticationProvider {

    private final AccountDetailService accountDetailService;

    private static final long TOKEN_VALIDATION_SECOND = 1000L * 60 * 120;
    private static final long REFRESH_TOKEN_VALIDATION_TIME = 1000L * 60 * 60 * 48;

    @Value("${spring.jwt.secret}")
    private String SECRET_KEY;

    @Value("${spring.jwt.issuer}")
    private String ISSUER;

    public String getEmailFromToken(String token) throws JWTVerificationException {
        DecodedJWT verifiedToken = validateToken(token);
        return verifiedToken.getClaim("email").asString();
    }

    public String generateToken(Map<String, String> payload) {
        return doGenerateToken(TOKEN_VALIDATION_SECOND, payload);
    }

    public String generateRefreshToken(Map<String, String> payload) {
        return doGenerateToken(REFRESH_TOKEN_VALIDATION_TIME, payload);
    }

    public boolean isTokenExpired(String token) {
        try {
            validateToken(token);
            return false;
        } catch (JWTVerificationException e) {
            return true;
        }
    }


    private Algorithm getSigningKey(String secretKey) {
        return Algorithm.HMAC256(secretKey);
    }

    private Map<String, Claim> getAllClaims(DecodedJWT token) {
        return token.getClaims();
    }

    private JWTVerifier getTokenValidator() {
        return JWT.require(getSigningKey(SECRET_KEY))
                .withIssuer(ISSUER)
                .build();
    }

    public DecodedJWT validateToken(String token) throws JWTVerificationException {
        JWTVerifier validator = getTokenValidator();
        return validator.verify(token);
    }

    public String doGenerateToken(long expireTime, Map<String, String> payload) {
        long now = System.currentTimeMillis();

        return JWT.create()
                .withIssuedAt(new Date(now))
                .withExpiresAt(new Date(now + expireTime))
                .withPayload(payload)
                .withIssuer(ISSUER)
                .sign(getSigningKey(SECRET_KEY));
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException, UsernameNotFoundException {
        AccountDetails userDetails = (AccountDetails) accountDetailService.loadUserByUsername(authentication.getName());

        return new UsernamePasswordAuthenticationToken(
                userDetails.getUsername(),
                userDetails.getPassword(),
                userDetails.getAuthorities()
        );
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}
