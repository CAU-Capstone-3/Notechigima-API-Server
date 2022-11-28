package com.capstone.notechigima.controller;

import com.capstone.notechigima.config.BaseResponse;
import com.capstone.notechigima.config.ExceptionCode;
import com.capstone.notechigima.config.SuccessCode;
import com.capstone.notechigima.config.auth.AccountDetailService;
import com.capstone.notechigima.config.auth.AccountDetails;
import com.capstone.notechigima.config.jwt.JwtProvider;
import com.capstone.notechigima.dto.auth.EmailDuplicationCheckPostRequestDTO;
import com.capstone.notechigima.dto.auth.LoginPostRequestDTO;
import com.capstone.notechigima.dto.auth.LoginPostResponseDTO;
import com.capstone.notechigima.dto.auth.SignupPostRequestDTO;
import com.capstone.notechigima.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
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
    private final UserService userService;

    @ResponseBody
    @PostMapping("/login")
    @Operation(summary = "로그인", description = "사용자 이메일(ID)과 비밀번호로 로그인")
    public BaseResponse<LoginPostResponseDTO> login(@RequestBody LoginPostRequestDTO body) throws UsernameNotFoundException {

        AccountDetails accountDetails = (AccountDetails) accountDetailService.loadUserByUsername(body.getEmail());
        if (accountDetails.getPassword().equals(body.getPassword())) {
            return new BaseResponse(SuccessCode.SUCCESS_READ, getAuthorizationDTO(accountDetails));
        } else {
            return new BaseResponse(ExceptionCode.ERROR_INVALID_PASSWORD);
        }
    }

    @ResponseBody
    @PostMapping("/duplication-check/email")
    @Operation(summary = "이메일 중복 확인", description = "회원가입 전 이메일 중복 여부 확인")
    public BaseResponse emailDuplicationCheck(@RequestBody EmailDuplicationCheckPostRequestDTO body) {
        return emailDuplicationCheck(body.getEmail());
    }

    @ResponseBody
    @PostMapping("/signup")
    @Operation(summary = "회원가입", description = "회원가입")
    public BaseResponse signup(@RequestBody SignupPostRequestDTO body) {
        // null 확인
        if (body.getEmail() == null || body.getNickname() == null || body.getPassword() == null)
            return new BaseResponse(ExceptionCode.ERROR_INVALID_REQUEST);

        // email 중복 확인
        BaseResponse emailCheck = emailDuplicationCheck(body.getEmail());
        if (emailCheck.getStatus() != 200)
            return emailCheck;

        int userId = userService.signup(body);
        return new BaseResponse(SuccessCode.SUCCESS_WRITE);
    }

    private BaseResponse emailDuplicationCheck(String email) {
        try {
            if (email != null)
                accountDetailService.loadUserByUsername(email);
            return new BaseResponse(ExceptionCode.ERROR_DUPLICATED_EMAIL);
        } catch (UsernameNotFoundException e) {
            return new BaseResponse(SuccessCode.EMAIL_AVAILABLE);
        }
    }

    private LoginPostResponseDTO getAuthorizationDTO(AccountDetails accountDetails) {
        Map<String, String> userMap = new HashMap<>();
        userMap.put("email", accountDetails.getUsername());

        String accessToken = jwtProvider.generateToken(userMap);

        return LoginPostResponseDTO.builder()
                .accessToken(accessToken)
                .userId(accountDetails.getUserId())
                .nickname(accountDetails.getNickname())
                .build();
    }

}
