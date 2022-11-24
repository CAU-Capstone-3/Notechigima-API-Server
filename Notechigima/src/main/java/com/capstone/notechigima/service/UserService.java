package com.capstone.notechigima.service;

import com.capstone.notechigima.dto.users.UserGetResponseDTO;

import java.util.List;

public interface UserService {
    List<UserGetResponseDTO> getMembersByGroupId(int groupId);
}
