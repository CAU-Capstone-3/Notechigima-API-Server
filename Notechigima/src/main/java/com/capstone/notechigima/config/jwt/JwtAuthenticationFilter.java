package com.capstone.notechigima.config.jwt;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.capstone.notechigima.config.ExceptionCode;
import lombok.RequiredArgsConstructor;
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

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    public static final String ACCESS_TOKEN_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";

    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Authentication authenticate;

        try {
            String accessToken = getToken(request);
            jwtProvider.validateToken(accessToken);

            String emailFromToken = jwtProvider.getEmailFromToken(accessToken);
            authenticate = jwtProvider.authenticate(new UsernamePasswordAuthenticationToken(emailFromToken, ""));

            SecurityContextHolder.getContext().setAuthentication(authenticate);

        } catch (SecurityException e) {
            request.setAttribute("exception", ExceptionCode.WRONG_TYPE_TOKEN.getHttpStatus());
        } catch (TokenExpiredException e) {
            request.setAttribute("exception", ExceptionCode.EXPIRED_TOKEN.getHttpStatus());
        } catch (UsernameNotFoundException e) {
            request.setAttribute("exception", ExceptionCode.ERROR_NOT_FOUND_USER.getHttpStatus());
        } catch (AuthenticationException e) {
            request.setAttribute("exception", ExceptionCode.WRONG_TOKEN.getHttpStatus());
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("exception", ExceptionCode.ERROR_UNKNOWN.getHttpStatus());
        }

        filterChain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest request) throws SecurityException {
        String token = request.getHeader(ACCESS_TOKEN_HEADER);
        if (token == null) {
            throw new SecurityException(ExceptionCode.WRONG_TOKEN.getMessage());
        }

        if (token.startsWith(BEARER_PREFIX)) {
            return token.substring(BEARER_PREFIX.length());
        }
        return token;
    }

}
