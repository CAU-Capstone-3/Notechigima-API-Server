package com.capstone.notechigima.service;

import com.capstone.notechigima.dto.ModelMapper;
import com.capstone.notechigima.dto.users.UserGetResponseDTO;
import com.capstone.notechigima.repository.GroupMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final GroupMemberRepository groupMemberRepository;
    private final ModelMapper modelMapper;

    public List<UserGetResponseDTO> getMembersByGroupId(int groupId) {
        return groupMemberRepository.findAllByStudyGroup_GroupId(groupId)
                .stream()
                .map(groupMember -> modelMapper.map(groupMember.getUser()))
                .collect(Collectors.toList());
    }
}
