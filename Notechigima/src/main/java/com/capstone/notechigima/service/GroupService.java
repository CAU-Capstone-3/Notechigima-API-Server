package com.capstone.notechigima.service;


import com.capstone.notechigima.dto.study_group.StudyGroupGetResponseDTO;
import com.capstone.notechigima.dto.study_group.StudyGroupPostRequestDTO;

import java.util.List;

public interface GroupService {
    List<StudyGroupGetResponseDTO> getGroups(int userId);
    int postGroup(StudyGroupPostRequestDTO body);
}
