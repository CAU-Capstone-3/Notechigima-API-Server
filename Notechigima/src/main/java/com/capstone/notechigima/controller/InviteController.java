package com.capstone.notechigima.controller;

import com.capstone.notechigima.config.BaseException;
import com.capstone.notechigima.config.BaseResponse;
import com.capstone.notechigima.config.BaseResponseStatus;
import com.capstone.notechigima.dto.invite.GroupInviteAcceptRequestDTO;
import com.capstone.notechigima.service.GroupInviteService;
import com.capstone.notechigima.service.GroupMemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "invite", description = "그룹 초대 관련 API")
@RestController
@RequestMapping("/api/invite")
@RequiredArgsConstructor
public class InviteController {

    private final GroupInviteService groupInviteService;
    private final GroupMemberService groupMemberService;

    @PostMapping("/accept")
    @Operation(summary = "그룹 초대 승인 또는 거절 (승인 -> true, 거절 -> false)", description = "사용자에게 온 그룹 초대를 승인하거나 거절")
    public BaseResponse postInviteAccept(@RequestBody GroupInviteAcceptRequestDTO body) throws BaseException {
        if (body.isAccept()) {
            groupInviteService.acceptInvite(body.getGroupInviteId());
            groupMemberService.joinMember(body.getGroupInviteId());
        } else {
            groupInviteService.declineInvite(body.getGroupInviteId());
        }
        return new BaseResponse(BaseResponseStatus.SUCCESS_WRITE);
    }

}
