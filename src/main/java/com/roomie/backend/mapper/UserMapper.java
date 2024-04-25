package com.roomie.backend.mapper;

import com.roomie.backend.controller.dto.UserProfileDto;
import com.roomie.backend.controller.dto.UserShortDto;
import com.roomie.backend.controller.dto.request.RegisterRequest;
import com.roomie.backend.model.User;
import lombok.experimental.UtilityClass;


@UtilityClass
public class UserMapper {

    public UserShortDto toUserShortDto(User user) {
        return UserShortDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .photoUrl(user.getPhotoUrl())
                .build();
    }

    public UserProfileDto toUserProfileDto(User user) {
        return UserProfileDto.builder()
                .id(user.getId())
                .name(user.getName())
                .gender(user.getGender())
                .email(user.getEmail())
                .photoUrl(user.getPhotoUrl())
                .build();
    }

    public User toUserEntity(RegisterRequest userRegisterDto) {
        return User.builder()
                .name(userRegisterDto.getName())
                .email(userRegisterDto.getEmail())
                .build();
    }
}