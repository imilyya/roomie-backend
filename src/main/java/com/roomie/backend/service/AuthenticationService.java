package com.roomie.backend.service;

import com.roomie.backend.controller.dto.request.AuthenticationRequest;
import com.roomie.backend.controller.dto.AuthenticationResponse;
import com.roomie.backend.controller.dto.request.RegisterRequest;

public interface AuthenticationService {

    public AuthenticationResponse register(RegisterRequest request);

    public AuthenticationResponse authenticate(AuthenticationRequest request);
}
