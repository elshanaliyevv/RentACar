package com.example.rentacar.service;

import com.example.rentacar.model.request.CarRegisterRequest;
import com.example.rentacar.model.response.CarResponse;

import java.util.List;

public interface CarService {
    CarResponse createCar(CarRegisterRequest carRegisterRequest);
    List<CarResponse> getAllCars();
    List<CarResponse> getCarByModel(String model);
    CarResponse getCarById(Integer id);
    boolean deleteCar(Integer id);
}
