package com.capstone.notechigima.service;

import com.capstone.notechigima.dto.users.GetUserResponseDTO;

import java.util.List;

public interface UserService {
    List<GetUserResponseDTO> getMembersByGroupId(int groupId);
}
