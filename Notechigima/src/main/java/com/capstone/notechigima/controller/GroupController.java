package com.capstone.notechigima.controller;

import com.capstone.notechigima.config.BaseException;
import com.capstone.notechigima.config.BaseResponse;
import com.capstone.notechigima.config.BaseResponseStatus;
import com.capstone.notechigima.model.dto.group.GetGroupResponseDTO;
import com.capstone.notechigima.model.dto.group.PostGroupRequestDTO;
import com.capstone.notechigima.service.GroupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "group", description = "그룹 관련 API")
@RestController
@RequestMapping("/api/group")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @ResponseBody
    @GetMapping("/{userId}")
    @Operation(summary = "그룹 조회", description = "사용자가 속한 그룹 목록 조회 API")
    public BaseResponse<List<GetGroupResponseDTO>> getGroupList(@PathVariable("userId") int userId) throws BaseException {
        return new BaseResponse(BaseResponseStatus.SUCCESS_READ, groupService.getGroups(userId));
    }

    @PostMapping()
    public BaseResponse postGroup(@RequestBody PostGroupRequestDTO body) throws BaseException {
        groupService.postGroup(body);
        return new BaseResponse(BaseResponseStatus.SUCCESS_EDIT);
    }
}
