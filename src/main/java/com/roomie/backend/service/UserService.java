package com.roomie.backend.service;

import com.roomie.backend.controller.dto.UserProfileDto;
import com.roomie.backend.controller.dto.UserRegisterDto;
import com.roomie.backend.controller.dto.UserShortDto;
import com.roomie.backend.entity.User;

import java.util.List;

public interface UserService {
    List<UserShortDto> getAllUsers();

    UserProfileDto createUser(UserRegisterDto userRegisterDto);

    UserProfileDto updateUser(Long id, UserProfileDto newUserInfo);

    void deleteUserById(Long id);

    UserProfileDto getUserById(Long id);
}
