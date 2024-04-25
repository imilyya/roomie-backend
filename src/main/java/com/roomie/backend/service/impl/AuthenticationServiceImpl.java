package com.roomie.backend.service.impl;

import com.roomie.backend.controller.dto.AuthenticationResponse;
import com.roomie.backend.controller.dto.request.AuthenticationRequest;
import com.roomie.backend.controller.dto.request.RegisterRequest;
import com.roomie.backend.dao.UserRepository;
import com.roomie.backend.exception.UserNotFoundException;
import com.roomie.backend.model.Role;
import com.roomie.backend.model.User;
import com.roomie.backend.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtServiceImpl jwtServiceImpl;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);
        var jwtToken = jwtServiceImpl.generateToken(user);

        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException("User not found!"));
        var jwtToken = jwtServiceImpl.generateToken(user);

        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
