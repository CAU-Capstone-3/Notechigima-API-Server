package com.capstone.notechigima.config.jwt;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.capstone.notechigima.config.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.expression.AccessException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.capstone.notechigima.config.jwt.JwtUtils.parseToken;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    public static final String ATTRIBUTE_EXCEPTION = "exception";

    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Authentication authenticate;

        try {
            String accessToken = parseToken(request.getHeader(JwtUtils.ACCESS_TOKEN_HEADER));
            jwtProvider.validateToken(accessToken);

            String emailFromToken = jwtProvider.getEmailFromToken(accessToken);
            authenticate = jwtProvider.authenticate(new UsernamePasswordAuthenticationToken(emailFromToken, ""));

            SecurityContextHolder.getContext().setAuthentication(authenticate);

        } catch (SecurityException e) {
            request.setAttribute(ATTRIBUTE_EXCEPTION, ExceptionCode.WRONG_TYPE_TOKEN.getHttpStatus());
        } catch (TokenExpiredException e) {
            request.setAttribute(ATTRIBUTE_EXCEPTION, ExceptionCode.EXPIRED_TOKEN.getHttpStatus());
        } catch (UsernameNotFoundException e) {
            request.setAttribute(ATTRIBUTE_EXCEPTION, ExceptionCode.ERROR_NOT_FOUND_USER.getHttpStatus());
        } catch (AuthenticationException e) {
            request.setAttribute(ATTRIBUTE_EXCEPTION, ExceptionCode.WRONG_TOKEN.getHttpStatus());
        } catch (IllegalArgumentException e) {
            request.setAttribute(ATTRIBUTE_EXCEPTION, ExceptionCode.ERROR_NOT_FOUND_RESOURCE);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute(ATTRIBUTE_EXCEPTION, ExceptionCode.ERROR_UNKNOWN.getHttpStatus());
        }

        response.addHeader("Access-Control-Allow-Origin", "*");
        filterChain.doFilter(request, response);
    }

}
