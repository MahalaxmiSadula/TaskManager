package com.code.authservice.service;

import com.code.authservice.dao.request.SignUpRequest;
import com.code.authservice.dao.request.SigninRequest;
import com.code.authservice.dao.response.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);
}