package com.roomie.backend.controller;

import com.roomie.backend.controller.dto.UserProfileDto;
import com.roomie.backend.controller.dto.UserRegisterDto;
import com.roomie.backend.controller.dto.UserShortDto;
import com.roomie.backend.entity.User;
import com.roomie.backend.exception.UserAlreadyExistsException;
import com.roomie.backend.exception.UserNotFoundException;
import com.roomie.backend.mapper.UserMapper;
import com.roomie.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<UserShortDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/register")
    public UserProfileDto createUser(@RequestBody UserRegisterDto userRegisterDto){
        return userService.createUser(userRegisterDto);
    }

    @PutMapping("/update/id={id}")
    public UserProfileDto updateUser(@PathVariable Long id, @RequestBody UserProfileDto newUserInfo) {
        return userService.updateUser(id, newUserInfo);
    }

    @DeleteMapping("/delete/id={id}")
    public void deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
    }

    @GetMapping("/id={id}")
    public UserProfileDto getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }
}
