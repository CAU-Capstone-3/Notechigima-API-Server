package com.capstone.notechigima.controller;

import com.capstone.notechigima.config.BaseResponse;
import com.capstone.notechigima.config.SuccessCode;
import com.capstone.notechigima.dto.study_group.StudyGroupPostRequestDTO;
import com.capstone.notechigima.dto.subject.SubjectGetResponseDTO;
import com.capstone.notechigima.dto.users.UserGetResponseDTO;
import com.capstone.notechigima.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.expression.AccessException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpHeaders;
import java.util.List;
import java.util.Map;

import static com.capstone.notechigima.config.jwt.JwtUtils.ACCESS_TOKEN_HEADER;

@Tag(name = "group", description = "그룹 관련 API")
@RestController
@RequestMapping("/api/groups")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;
    private final UserService userService;
    private final GroupInviteService groupInviteService;
    private final SubjectService subjectService;
    private final AuthService authService;

    @PostMapping()
    @Operation(summary = "그룹 추가", description = "그룹 생성 후 사용자가 그룹의 관리자로 등록됨")
    public BaseResponse postGroup(@RequestBody StudyGroupPostRequestDTO body) {
        groupService.postStudyGroup(body);
        return new BaseResponse(SuccessCode.SUCCESS_WRITE);
    }

    @ResponseBody
    @GetMapping("/{groupId}/members")
    @Operation(summary = "멤버 조회", description = "그룹에 속한 멤버 목록 조회")
    public BaseResponse<List<UserGetResponseDTO>> getMembersByGroup(
            HttpServletRequest request,
            @PathVariable("groupId") int groupId
            ) throws AccessException {

        authService.authorizationByGroupId(request.getHeader(ACCESS_TOKEN_HEADER), groupId);
        return new BaseResponse(SuccessCode.SUCCESS_READ, userService.getMembersByGroupId(groupId));
    }

    @ResponseBody
    @GetMapping("/{groupId}/subjects")
    @Operation(summary = "과목 목록 조회", description = "그룹에 속한 전체 과목 목록을 조회")
    public BaseResponse<List<SubjectGetResponseDTO>> getSubjectsByGroup(
            HttpServletRequest request,
            @PathVariable("groupId") int groupId
            ) throws AccessException {

        authService.authorizationByGroupId(request.getHeader(ACCESS_TOKEN_HEADER) ,groupId);
        return new BaseResponse(SuccessCode.SUCCESS_READ, subjectService.getSubjectsByGroupId(groupId));
    }

}
