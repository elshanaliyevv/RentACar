package com.example.rentacar.service;

import com.example.rentacar.model.request.CarRegisterRequest;
import com.example.rentacar.model.response.CarResponse;

import java.util.List;

public interface CarService {
    CarResponse registerCar(CarRegisterRequest carRegisterRequest);

    List<CarResponse> getAllCars();

    List<CarResponse> getCarByModel(String model);

    CarResponse getCarById(Long id);

    boolean deleteCar(Long id);

    CarResponse updateCar(Long id, CarRegisterRequest request);
}
