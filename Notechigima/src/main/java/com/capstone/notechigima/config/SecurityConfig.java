package com.capstone.notechigima.config;

import com.capstone.notechigima.domain.users.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().mvcMatchers(
                "/swagger-ui/**",
                "/error"
                ,"/login/**"
                );
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http.antMatcher("/**")
                .cors().disable()
                .csrf().disable()
                .httpBasic().disable()
                .formLogin().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .authorizeRequests()
                .antMatchers("/admin/").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/api/**").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")


                .anyRequest().permitAll()
//                .and()
//                .addFilterBefore(jwtFilter)
//                .exceptionHandling()
//                .authenticationEntryPoint(((request, response, authException) -> {
//                    response.setStatus(HttpStatus.UNAUTHORIZED.value());
//                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//                    objectMapper.writeValue(
//                            response.getOutputStream(),
//                            ExceptionResponse.of(ExceptionCode.FAIL_AUTHENTICATION)
//                    );
//                }))
//                .accessDeniedHandler(((request, response, accessDeniedException) -> {
//                    response.setStatus(HttpStatus.FORBIDDEN.value());
//                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//                    objectMapper.writeValue(
//                            response.getOutputStream(),
//                            ExceptionResponse.of(ExceptionCode.FAIL_AUTHORIZATION)
//                    );
//                })).and()
    .build();
    }
}
