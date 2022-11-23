package com.capstone.notechigima.service;

import com.capstone.notechigima.model.ModelMapper;
import com.capstone.notechigima.model.dto.group.GetGroupResponseDTO;
import com.capstone.notechigima.model.dto.group.PostGroupRequestDTO;
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
        return groupRepository.getGroups(userId).stream()
                .map(entity -> modelMapper.map(entity))
                .collect(Collectors.toList());
    }

    @Override
    public int postGroup(PostGroupRequestDTO body) {
        return groupRepository.insertGroup(
                modelMapper.map(body)
        );
    }
}
