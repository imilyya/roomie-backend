package com.roomie.backend.service.impl;

import com.roomie.backend.controller.dto.UserProfileDto;
import com.roomie.backend.controller.dto.UserRegisterDto;
import com.roomie.backend.controller.dto.UserShortDto;
import com.roomie.backend.dao.UserRepository;
import com.roomie.backend.entity.User;
import com.roomie.backend.exception.UserAlreadyExistsException;
import com.roomie.backend.exception.UserNotFoundException;
import com.roomie.backend.mapper.UserMapper;
import com.roomie.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public List<UserShortDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toUserShortDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserProfileDto createUser(UserRegisterDto userRegisterDto){
        if (userRepository.findByEmail(userRegisterDto.getEmail()).isPresent()){
            throw new UserAlreadyExistsException("User already exists!");
        }

        User user = UserMapper.toUserEntity(userRegisterDto);

        return UserMapper.toUserProfileDto(userRepository.saveAndFlush(user));
    }

    @Override
    public UserProfileDto updateUser(Long id, UserProfileDto newUserInfo) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("User not found!");
        }

        User user = userOptional.get();
        if (!newUserInfo.getName().isEmpty()) user.setName(newUserInfo.getName());
        if (!newUserInfo.getEmail().isEmpty()) user.setEmail(newUserInfo.getEmail());
        if (!newUserInfo.getGender().isEmpty()) user.setGender(newUserInfo.getGender());
        if (!newUserInfo.getPhotoUrl().isEmpty()) user.setPhotoUrl(newUserInfo.getPhotoUrl());

        return UserMapper.toUserProfileDto(userRepository.save(user));
    }

    @Override
    public void deleteUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with ID" + id + "doesn't exist!"));

        userRepository.delete(user);
    }

    @Override
    public UserProfileDto getUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isEmpty()) throw new UserNotFoundException("User with ID " + id + " not found");

        return UserMapper.toUserProfileDto(userOptional.get());
    }
}
