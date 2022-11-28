package com.capstone.notechigima.controller;

import com.capstone.notechigima.config.BaseResponse;
import com.capstone.notechigima.config.BaseResponseStatus;
import com.capstone.notechigima.config.auth.AccountDetailService;
import com.capstone.notechigima.config.jwt.JwtProvider;
import com.capstone.notechigima.dto.auth.LoginPostRequestDTO;
import com.capstone.notechigima.dto.auth.LoginPostResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Tag(name = "auth", description = "사용자 인증 관련 API")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AccountDetailService accountDetailService;
    private final JwtProvider jwtProvider;

    @ResponseBody
    @PostMapping("/login")
    @Operation(summary = "로그인", description = "사용자 이메일(ID)과 비밀번호로 로그인")
    public BaseResponse<LoginPostResponseDTO> login(@RequestBody LoginPostRequestDTO body) {
        try {
            UserDetails userDetails = accountDetailService.loadUserByUsername(body.getEmail());
            if (userDetails.getPassword().equals(body.getPassword())) {
                return new BaseResponse(BaseResponseStatus.SUCCESS_READ, getAuthorizationDTO(userDetails));
            } else {
                return new BaseResponse(BaseResponseStatus.ERROR_INVALID_PASSWORD);
            }

        } catch (UsernameNotFoundException e) {
            return new BaseResponse(BaseResponseStatus.ERROR_NOT_FOUND_USER);
        }
    }

    private LoginPostResponseDTO getAuthorizationDTO(UserDetails userDetails) {
        Map<String, String> userMap = new HashMap<>();
        userMap.put("email", userDetails.getUsername());

        String accessToken = jwtProvider.generateToken(userMap);

        return LoginPostResponseDTO.builder()
                .accessToken(accessToken)
                .build();
    }

}
