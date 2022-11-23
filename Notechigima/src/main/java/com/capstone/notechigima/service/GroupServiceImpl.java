package com.capstone.notechigima.service;

import com.capstone.notechigima.dto.ModelMapper;
import com.capstone.notechigima.domain.study_group.GroupCreateEntity;
import com.capstone.notechigima.domain.users.MemberEntity;
import com.capstone.notechigima.dto.group.GetGroupResponseDTO;
import com.capstone.notechigima.dto.group.PostGroupRequestDTO;
import com.capstone.notechigima.repository.GroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final ModelMapper modelMapper;

    public GroupServiceImpl(GroupRepository groupRepository, ModelMapper modelMapper) {
        this.groupRepository = groupRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<GetGroupResponseDTO> getGroups(int userId) {
        return groupRepository.getGroupsByUserId(userId).stream()
                .map(entity -> modelMapper.map(entity))
                .collect(Collectors.toList());
    }

    @Override
    public int postGroup(PostGroupRequestDTO body) {
        GroupCreateEntity entity = modelMapper.map(body);
        groupRepository.insertGroup(entity);
        groupRepository.insertMember(new MemberEntity(
                body.getUserId(),
                entity.getGroupId(),
                'O'
        ));
        return entity.getGroupId();
    }
}
