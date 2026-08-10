package com.example.rentacar.service;

import com.example.rentacar.model.request.LoginRequest;
import com.example.rentacar.model.request.UserRegisterRequest;
import com.example.rentacar.model.response.TokensResponse;

public interface UserService {
    public TokensResponse register(UserRegisterRequest userRegisterRequest);
    public TokensResponse login(LoginRequest loginRequest);
}
