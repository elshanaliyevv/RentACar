package com.example.rentacar.service;

import com.example.rentacar.exception.CarAlreadyExistException;
import com.example.rentacar.exception.CarNotFoundException;
import com.example.rentacar.exception.NumberAlreadyExistException;
import com.example.rentacar.mapper.Mapper;
import com.example.rentacar.model.entity.Car;
import com.example.rentacar.model.request.CarRegisterRequest;
import com.example.rentacar.model.response.CarResponse;
import com.example.rentacar.repository.CarRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final Mapper mapper;

    @Override
    public CarResponse registerCar(CarRegisterRequest carRegisterRequest) {
        if (carRepository.existsByPlateNumber(carRegisterRequest.getPlateNumber())) {
            throw new CarAlreadyExistException("Bu neqliyyat artiq movcuddur");
        }
        Car car = carRepository.save(mapper.toCar(carRegisterRequest));
        return mapper.toCarResponse(car);
    }

    @Override
    public List<CarResponse> getAllCars() {
        return carRepository.findAll().stream()
                .map(mapper::toCarResponse)
                .toList();
    }

    @Override
    public List<CarResponse> getCarByModel(String model) {
        List<Car> cars = carRepository.findCarByModel(model);
        if (cars.isEmpty()) {
            throw new CarNotFoundException("Bele bir model yoxdur");
        }
        return cars.stream()
                .map(mapper::toCarResponse)
                .toList();
    }

    @Override
    public CarResponse getCarById(Long id) {
        Optional<Car> car = carRepository.findById(id);

        if (!car.isPresent()) {
            throw new CarNotFoundException(id + " bu id ye mexsus masin yoxdur");
        }
        return mapper.toCarResponse(car.get());

    }

    @Transactional
    @Override
    public boolean deleteCar(Long id) {
        Optional<Car> car = carRepository.findById(id);

        if (!car.isPresent()) {
            throw new CarNotFoundException(id + " bu id ye mexsus masin yoxdur");
        }
        carRepository.deleteById(id);
        return true;
    }

    @Transactional
    @Override
    public CarResponse updateCar(Long id, CarRegisterRequest request) {
        Optional<Car> car = carRepository.findById(id);
        if (!car.isPresent()) {
            throw new CarNotFoundException(id + " bu id ye mexsus masin movcud deyil");
        }
        Car carWithSamePlate = carRepository.findCarByPlateNumber(request.getPlateNumber());
        if (carWithSamePlate != null && !carWithSamePlate.getId().equals(id)) {
            throw new NumberAlreadyExistException(request.getPlateNumber() + " bu nomrede masin sistemde movcuddur");
        }
        Car existingCar = car.get();
        existingCar.setBrand(request.getBrand());
        existingCar.setModel(request.getModel());
        existingCar.setYear(request.getYear());
        existingCar.setPricePerDay(request.getPricePerDay());
        existingCar.setColor(request.getColor());
        existingCar.setPlateNumber(request.getPlateNumber());
        existingCar.setFuelType(request.getFuelType());
        existingCar.setTransmissionType(request.getTransmissionType());
        existingCar.setSeatingCapacity(request.getSeatingCapacity());
        existingCar.setImageUrl(request.getImageUrl());
        existingCar.setDescription(request.getDescription());

        return mapper.toCarResponse(carRepository.save(existingCar));
    }

}
