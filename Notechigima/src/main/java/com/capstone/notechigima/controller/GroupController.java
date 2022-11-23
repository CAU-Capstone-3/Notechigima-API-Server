package com.capstone.notechigima.controller;

import com.capstone.notechigima.config.BaseException;
import com.capstone.notechigima.config.BaseResponse;
import com.capstone.notechigima.config.BaseResponseStatus;
import com.capstone.notechigima.dto.group.PostGroupRequestDTO;
import com.capstone.notechigima.dto.users.GetUserResponseDTO;
import com.capstone.notechigima.service.GroupInviteService;
import com.capstone.notechigima.service.GroupService;
import com.capstone.notechigima.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "group", description = "그룹 관련 API")
@RestController
@RequestMapping("/api/group")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;
    private final UserService userService;
    private final GroupInviteService groupInviteService;

    @PostMapping()
    @Operation(summary = "그룹 추가", description = "그룹 생성 후 사용자가 그룹의 관리자로 등록됨")
    public BaseResponse postGroup(@RequestBody PostGroupRequestDTO body) throws BaseException {
        groupService.postGroup(body);
        return new BaseResponse(BaseResponseStatus.SUCCESS_WRITE);
    }

    @ResponseBody
    @GetMapping("/{groupId}/members")
    @Operation(summary = "멤버 조회", description = "그룹에 속한 멤버 목록 조회")
    public BaseResponse<List<GetUserResponseDTO>> getMembersByGroupId(@PathVariable("groupId") int groupId) throws BaseException {
        return new BaseResponse(BaseResponseStatus.SUCCESS_READ, userService.getMembersByGroupId(groupId));
    }

    @ResponseBody
    @PostMapping("/{groupId}/invite/{userId}")
    @Operation(summary = "멤버 초대", description = "그룹에 멤버를 초대")
    public BaseResponse postInvite(
            @Schema(description = "그룹 ID", defaultValue = "1") @PathVariable("groupId") int groupId,
            @Schema(description = "초대할 사용자 ID", defaultValue = "4") @PathVariable("userId") int userId
    ) throws BaseException {
        groupInviteService.save(groupId, userId);
        return new BaseResponse(BaseResponseStatus.SUCCESS_WRITE);
    }
}
