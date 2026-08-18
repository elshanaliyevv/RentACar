package com.example.rentacar.mapper;

import com.example.rentacar.enums.Roles;
import com.example.rentacar.model.entity.Car;
import com.example.rentacar.model.entity.User;
import com.example.rentacar.model.request.CarRegisterRequest;
import com.example.rentacar.model.request.UserRegisterRequest;
import com.example.rentacar.model.response.CarResponse;
import org.springframework.stereotype.Service;

@Service
public class Mapper {
    public User toUser(UserRegisterRequest userRegisterRequest) {
        return User.builder()
                .username(userRegisterRequest.getUsername())
                .email(userRegisterRequest.getEmail())
                .number(userRegisterRequest.getNumber())
                .role(Roles.USER)
                .isActive(true)
                .build();
    }

    public Car carRegisterRequestToCar(CarRegisterRequest carRegisterRequest) {
        return Car.builder()
                .brand(carRegisterRequest.getBrand())
                .color(carRegisterRequest.getColor())
                .description(carRegisterRequest.getDescription())
                .fuelType(carRegisterRequest.getFuelType())
                .model(carRegisterRequest.getModel())
                .plateNumber(carRegisterRequest.getPlateNumber())
                .pricePerDay(carRegisterRequest.getPricePerDay())
                .imageUrl(carRegisterRequest.getImageUrl())
                .transmissionType(carRegisterRequest.getTransmissionType())
                .year(carRegisterRequest.getYear())
                .seatingCapacity(carRegisterRequest.getSeatingCapacity())
                .build();
    }

    public CarResponse toCarResponse(Car car) {
        return CarResponse.builder()
                .id(car.getId())
                .brand(car.getBrand())
                .color(car.getColor())
                .description(car.getDescription())
                .fuelType(car.getFuelType())
                .imageUrl(car.getImageUrl())
                .model(car.getModel())
                .plateNumber(car.getPlateNumber())
                .pricePerDay(car.getPricePerDay())
                .seatingCapacity(car.getSeatingCapacity())
                .transmissionType(car.getTransmissionType())
                .year(car.getYear())
                .status(car.getStatus())
                .build();

    }
}
