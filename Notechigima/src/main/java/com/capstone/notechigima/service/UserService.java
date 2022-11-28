package com.capstone.notechigima.service;

import com.capstone.notechigima.domain.ActiveStatus;
import com.capstone.notechigima.domain.users.User;
import com.capstone.notechigima.domain.users.UserRole;
import com.capstone.notechigima.dto.auth.SignupPostRequestDTO;
import com.capstone.notechigima.dto.users.UserGetResponseDTO;
import com.capstone.notechigima.mapper.UserMapper;
import com.capstone.notechigima.repository.GroupMemberRepository;
import com.capstone.notechigima.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final GroupMemberRepository groupMemberRepository;
    private final PasswordEncoder bCryptPasswordEncoder;

    public List<UserGetResponseDTO> getMembersByGroupId(int groupId) {
        return groupMemberRepository.findAllByStudyGroup_GroupId(groupId)
                .stream()
                .map(groupMember -> UserMapper.INSTANCE.toUserGetResponseDTO(groupMember.getUser()))
                .collect(Collectors.toList());
    }

    /**
     * 회원가입 후 userId 반환
     * @param body 회원가입 정보
     * @return 생성된 userId
     */
    public int signup(SignupPostRequestDTO body) {
        User user = User.builder()
                .email(body.getEmail())
                .nickname(body.getNickname())
                .password(body.getPassword())
                .role(UserRole.USER)
                .status(ActiveStatus.ACTIVE)
                .build();

        user.hashPassword(bCryptPasswordEncoder);
        userRepository.save(user);
        return user.getUserId();
    }

}
