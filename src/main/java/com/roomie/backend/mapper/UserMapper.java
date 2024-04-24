package com.roomie.backend.mapper;

import com.roomie.backend.controller.dto.UserProfileDto;
import com.roomie.backend.controller.dto.UserRegisterDto;
import com.roomie.backend.controller.dto.UserShortDto;
import com.roomie.backend.entity.User;
import lombok.experimental.UtilityClass;


@UtilityClass
public class UserMapper {
    public User toUserEntity(UserRegisterDto userRegisterDto) {
        User user = User.builder()
                .name(userRegisterDto.getName())
                .email(userRegisterDto.getEmail())
                .password(userRegisterDto.getPassword())
                .build();

        return user;
    }

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
}
