package com.capstone.notechigima.service;

import com.capstone.notechigima.dto.users.UserGetResponseDTO;
import com.capstone.notechigima.mapper.UserMapper;
import com.capstone.notechigima.repository.GroupMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final GroupMemberRepository groupMemberRepository;

    public List<UserGetResponseDTO> getMembersByGroupId(int groupId) {
        return groupMemberRepository.findAllByStudyGroup_GroupId(groupId)
                .stream()
                .map(groupMember -> UserMapper.INSTANCE.toUserGetResponseDTO(groupMember.getUser()))
                .collect(Collectors.toList());
    }

}
