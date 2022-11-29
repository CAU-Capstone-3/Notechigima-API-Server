package com.capstone.notechigima.controller;

import com.capstone.notechigima.config.BaseResponse;
import com.capstone.notechigima.config.SuccessCode;
import com.capstone.notechigima.dto.invite.GroupInviteReceivedGetResponseDTO;
import com.capstone.notechigima.dto.study_group.StudyGroupGetResponseDTO;
import com.capstone.notechigima.service.AuthService;
import com.capstone.notechigima.service.GroupInviteService;
import com.capstone.notechigima.service.GroupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.expression.AccessException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.capstone.notechigima.config.jwt.JwtUtils.ACCESS_TOKEN_HEADER;

@Tag(name = "users", description = "유저 관련 API")
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final GroupService groupService;
    private final GroupInviteService groupInviteService;
    private final AuthService authService;

    @ResponseBody
    @GetMapping("/{userId}/groups")
    @Operation(summary = "그룹 조회", description = "사용자가 속한 그룹 목록 조회")
    public BaseResponse<List<StudyGroupGetResponseDTO>> getGroupList(
            HttpServletRequest request,
            @PathVariable("userId") int userId
    ) throws AccessException {

        authService.authorizationByUserId(request.getHeader(ACCESS_TOKEN_HEADER), userId);
        return new BaseResponse(SuccessCode.SUCCESS_READ, groupService.getStudyGroupsByUserId(userId));
    }

    @ResponseBody
    @GetMapping("/{userId}/invites")
    @Operation(summary = "초대된 그룹 목록", description = "나한테 그룹 초대를 요청한 목록 조회")
    public BaseResponse<List<GroupInviteReceivedGetResponseDTO>> getGroupInvitedList(
            HttpServletRequest request,
            @PathVariable("userId") int userId
    ) throws AccessException {

        authService.authorizationByUserId(request.getHeader(ACCESS_TOKEN_HEADER), userId);
        return new BaseResponse(SuccessCode.SUCCESS_READ, groupInviteService.getGroupInvitedByUserId(userId));
    }
}
