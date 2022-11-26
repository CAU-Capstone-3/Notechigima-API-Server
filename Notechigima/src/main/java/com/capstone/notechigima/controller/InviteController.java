package com.capstone.notechigima.controller;

import com.capstone.notechigima.config.BaseException;
import com.capstone.notechigima.config.BaseResponse;
import com.capstone.notechigima.config.BaseResponseStatus;
import com.capstone.notechigima.dto.invite.GroupInviteAcceptRequestDTO;
import com.capstone.notechigima.dto.invite.GroupInvitePostRequestDTO;
import com.capstone.notechigima.service.GroupInviteService;
import com.capstone.notechigima.service.GroupMemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "invite", description = "그룹 초대 관련 API")
@RestController
@RequestMapping("/api/invite")
@RequiredArgsConstructor
public class InviteController {

    private final GroupInviteService groupInviteService;
    private final GroupMemberService groupMemberService;

    @PostMapping("/accept")
    @Operation(summary = "그룹 초대 승인 또는 거절", description = "사용자에게 온 그룹 초대를 승인하거나 거절 (승인 -> true, 거절 -> false)")
    public BaseResponse postInviteAccept(@RequestBody GroupInviteAcceptRequestDTO body) throws BaseException {
        if (body.isAccept()) {
            groupInviteService.acceptInvite(body.getGroupInviteId());
            groupMemberService.joinMember(body.getGroupInviteId());
        } else {
            groupInviteService.declineInvite(body.getGroupInviteId());
        }
        return new BaseResponse(BaseResponseStatus.SUCCESS_WRITE);
    }

    @ResponseBody
    @PostMapping()
    @Operation(summary = "멤버 초대", description = "그룹에 멤버를 초대")
    public BaseResponse postInvite(@RequestBody GroupInvitePostRequestDTO body) throws BaseException {
        groupInviteService.postGroupInvite(body);
        return new BaseResponse(BaseResponseStatus.SUCCESS_WRITE);
    }

}