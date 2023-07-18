package com.pairprogrmmingbackend.pairprogramming.mappers;

import com.pairprogrmmingbackend.pairprogramming.dtos.SignUpDto;
import com.pairprogrmmingbackend.pairprogramming.dtos.UserDto;
import com.pairprogrmmingbackend.pairprogramming.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toUserDto(User user);

    @Mapping(target = "password", ignore = true)
    User signUpToUser(SignUpDto signUpDto);

}