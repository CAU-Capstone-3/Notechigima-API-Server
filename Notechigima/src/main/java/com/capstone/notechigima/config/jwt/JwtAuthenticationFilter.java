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

    public static final String ACCESS_TOKEN_HEADER = "access_token";
    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Authentication authenticate;

        String accessToken = request.getHeader(ACCESS_TOKEN_HEADER);
        try {
            if (accessToken == null) {
                throw new SecurityException(ExceptionCode.WRONG_TOKEN.getMessage());
            }
            jwtProvider.validateToken(accessToken);

            String emailFromToken = jwtProvider.getEmailFromToken(accessToken);
            authenticate = jwtProvider.authenticate(new UsernamePasswordAuthenticationToken(emailFromToken, ""));

            SecurityContextHolder.getContext().setAuthentication(authenticate);

        } catch (SecurityException e) {
            request.setAttribute("exception", ExceptionCode.WRONG_TYPE_TOKEN.getCode());
        } catch (TokenExpiredException e) {
            request.setAttribute("exception", ExceptionCode.EXPIRED_TOKEN.getCode());
        } catch (UsernameNotFoundException e) {
            request.setAttribute("exception", ExceptionCode.ERROR_NOT_FOUND_USER.getCode());
        } catch (AuthenticationException e) {
            request.setAttribute("exception", ExceptionCode.WRONG_TOKEN.getCode());
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("exception", ExceptionCode.ERROR_UNKNOWN.getCode());
        }

        filterChain.doFilter(request, response);
    }
}
