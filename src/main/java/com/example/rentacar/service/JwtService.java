package com.example.rentacar.service;

import com.example.rentacar.model.response.TokensResponse;

public interface JwtService {
    TokensResponse generateTokens(String username);
    String extractUsername(String token);
    boolean isTokenValid(String token);
    String generateRefreshToken(String username);
    String generateAccessToken(String username);
}
