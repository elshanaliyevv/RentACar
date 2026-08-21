package com.example.rentacar.controller;

import com.example.rentacar.model.request.CarRegisterRequest;
import com.example.rentacar.model.response.CarResponse;
import com.example.rentacar.service.CarServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
@RequiredArgsConstructor
public class CarController {
    private final CarServiceImpl carService;

    @PostMapping("/register")
    public ResponseEntity<CarResponse> registerCar(@Valid @RequestBody CarRegisterRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(carService.registerCar(request));
    }

    @PutMapping("/updatecar/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CarResponse> updateCar(@PathVariable Long id, @Valid @RequestBody CarRegisterRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(carService.updateCar(id, request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarResponse> getCarById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(carService.getCarById(id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping()
    public ResponseEntity<List<CarResponse>> getAllCar() {
        return ResponseEntity.status(HttpStatus.OK).body(carService.getAllCars());
    }

    @GetMapping("/model/{model}")
    public ResponseEntity<List<CarResponse>> getCarByModel(@PathVariable String model) {
        return ResponseEntity.status(HttpStatus.OK).body(carService.getCarByModel(model));
    }
}
