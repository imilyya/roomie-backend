package com.roomie.backend.service;

import com.roomie.backend.controller.dto.UserProfileDto;
import com.roomie.backend.controller.dto.UserShortDto;
import com.roomie.backend.controller.dto.request.RegisterRequest;

import java.util.List;

public interface UserService {
    List<UserShortDto> getAllUsers();

//    UserProfileDto createUser(RegisterRequest userRegisterDto);

    UserProfileDto updateUser(Long id, UserProfileDto newUserInfo);

    void deleteUserById(Long id);

    UserProfileDto getUserById(Long id);
}
