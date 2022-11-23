package com.capstone.notechigima.service;

import com.capstone.notechigima.model.ModelMapper;
import com.capstone.notechigima.model.dto.users.GetUserResponseDTO;
import com.capstone.notechigima.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<GetUserResponseDTO> getMembersByGroupId(int groupId) {
        return userRepository.getMembersByGroupId(groupId).stream()
                .map(entity -> modelMapper.map(entity))
                .collect(Collectors.toList());
    }
}
