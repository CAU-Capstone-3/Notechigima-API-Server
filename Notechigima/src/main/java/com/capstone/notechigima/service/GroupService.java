package com.capstone.notechigima.service;


import com.capstone.notechigima.dto.group.GetGroupResponseDTO;
import com.capstone.notechigima.dto.group.PostGroupRequestDTO;

import java.util.List;

public interface GroupService {
    List<GetGroupResponseDTO> getGroups(int userId);
    int postGroup(PostGroupRequestDTO body);
}
