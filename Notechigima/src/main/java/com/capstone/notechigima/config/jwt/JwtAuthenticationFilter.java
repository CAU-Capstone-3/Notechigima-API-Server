package com.capstone.notechigima.config.jwt;

import com.capstone.notechigima.dto.auth.LoginPostRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IllegalArgumentException {

        ObjectMapper om = new ObjectMapper();
        LoginPostRequestDTO loginRequestDto = null;

        try {
            loginRequestDto = om.readValue(request.getInputStream(), LoginPostRequestDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (loginRequestDto.getEmail() == null || loginRequestDto.getPassword() == null) {
            throw new IllegalArgumentException("올바르지 않은 입력");
        }

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        loginRequestDto.getEmail(),
                        loginRequestDto.getPassword());

        Authentication authentication =
                authenticationManager.authenticate(authenticationToken);

        PrincipalDetails principalDetailis = (PrincipalDetails) authentication.getPrincipal();
        System.out.println("Authentication : "+principalDetailis.getUser().getUsername());
        return authentication;
    }
}
