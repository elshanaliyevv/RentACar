package com.example.rentacar.mapper;

import com.example.rentacar.enums.Roles;
import com.example.rentacar.model.entity.User;
import com.example.rentacar.model.request.UserRegisterRequest;
import org.springframework.stereotype.Service;

@Service
public class Mapper {
    public User toUser (UserRegisterRequest userRegisterRequest){
        return User.builder()
                .username(userRegisterRequest.getUsername())
                .email(userRegisterRequest.getEmail())
                .number(userRegisterRequest.getNumber())
                .role(Roles.USER)
                .isActive(true)
                .build();
    }
}
