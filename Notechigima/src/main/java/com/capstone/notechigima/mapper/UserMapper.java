package com.capstone.notechigima.mapper;

import com.capstone.notechigima.domain.users.User;
import com.capstone.notechigima.dto.users.UserGetResponseDTO;
import com.capstone.notechigima.dto.users.UserNicknameGetResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserGetResponseDTO toUserGetResponseDTO(User entity);

    @Mapping(target = "userName", source = "nickname")
    UserNicknameGetResponseDTO toUserNicknameGetResponseDTO(User entity);
}
