package com.capstone.notechigima.service;

import com.capstone.notechigima.dto.users.UserReadResponseDTO;

import java.util.List;

public interface UserService {
    List<UserReadResponseDTO> getMembersByGroupId(int groupId);
}
