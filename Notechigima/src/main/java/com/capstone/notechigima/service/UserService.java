package com.capstone.notechigima.service;

import com.capstone.notechigima.model.users.GetUserResponseDTO;

import java.util.List;

public interface UserService {
    List<GetUserResponseDTO> getMembersByGroupId(int groupId);
}
