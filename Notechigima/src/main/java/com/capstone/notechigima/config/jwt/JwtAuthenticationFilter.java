package com.capstone.notechigima.config.jwt;

import com.capstone.notechigima.dto.auth.LoginPostRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {

    private final JwtProvider jwtProvider;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Authentication authenticate;

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String accessToken = req.getHeader("access_token");
        if (accessToken != null && !jwtProvider.isTokenExpired(accessToken)) {
            try {
                String emailFromToken = jwtProvider.getEmailFromToken(accessToken);
                authenticate = jwtProvider.authenticate(new UsernamePasswordAuthenticationToken(emailFromToken, ""));

                SecurityContextHolder.getContext().setAuthentication(authenticate);
            } catch (Exception e) {
                res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                res.setContentType("application/json");
                res.setCharacterEncoding("UTF-8");

                JSONObject responseJson = new JSONObject();
                responseJson.put("code", 401);
                responseJson.put("message", e.getMessage());

                res.getWriter().write(responseJson.toString());
            }
        }

        chain.doFilter(request, response);
    }
}
