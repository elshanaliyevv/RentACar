package com.example.rentacar.service;

import com.example.rentacar.model.request.LoginRequest;
import com.example.rentacar.model.request.UserRegisterRequest;
import com.example.rentacar.model.response.TokensResponse;

public interface UserService {
    TokensResponse register(UserRegisterRequest userRegisterRequest);

    TokensResponse login(LoginRequest loginRequest);

    TokensResponse refreshToken(String refreshToken);
}
