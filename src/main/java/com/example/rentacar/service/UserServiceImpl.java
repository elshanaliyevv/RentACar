package com.example.rentacar.service;

import com.example.rentacar.exception.UserAlreadyExistsException;
import com.example.rentacar.mapper.Mapper;
import com.example.rentacar.model.entity.User;
import com.example.rentacar.model.request.LoginRequest;
import com.example.rentacar.model.request.UserRegisterRequest;
import com.example.rentacar.model.response.TokensResponse;
import com.example.rentacar.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final Mapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Override
    public TokensResponse register(UserRegisterRequest userRegisterRequest) {
        if (userRepo.existsByEmail(userRegisterRequest.getEmail())){
            throw new UserAlreadyExistsException("Bu username artiq movcuddur");
        }
        if (userRepo.existsByNumber(userRegisterRequest.getNumber())) {
            throw new UserAlreadyExistsException("Bu nomre artıq istifadə olunub");
        }
        if (userRepo.existsByUsername(userRegisterRequest.getUsername())) {
            throw new UserAlreadyExistsException("Bu nomre artıq istifadə olunub");
        }
        User user = mapper.toUser(userRegisterRequest);
        user.setPassword(passwordEncoder.encode(userRegisterRequest.getPassword()));
        userRepo.save(user);
        return jwtService.generateTokens(user.getUsername());
    }

    @Override
    public TokensResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));
        return jwtService.generateTokens(loginRequest.getUsername());
    }
}
