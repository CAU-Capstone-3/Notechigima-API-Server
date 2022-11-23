package com.capstone.notechigima.controller;

import com.capstone.notechigima.config.BaseException;
import com.capstone.notechigima.config.BaseResponse;
import com.capstone.notechigima.config.BaseResponseStatus;
import com.capstone.notechigima.dto.group.GetGroupResponseDTO;
import com.capstone.notechigima.service.GroupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "users", description = "유저 관련 API")
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final GroupService groupService;

    public UserController(GroupService groupService) {
        this.groupService = groupService;
    }

    @ResponseBody
    @GetMapping("/{userId}/group")
    @Operation(summary = "그룹 조회", description = "사용자가 속한 그룹 목록 조회 API")
    public BaseResponse<List<GetGroupResponseDTO>> getGroupList(@PathVariable("userId") int userId) throws BaseException {
        return new BaseResponse(BaseResponseStatus.SUCCESS_READ, groupService.getGroups(userId));
    }
}
