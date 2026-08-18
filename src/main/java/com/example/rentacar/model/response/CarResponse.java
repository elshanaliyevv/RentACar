package com.example.rentacar.model.response;

import com.example.rentacar.enums.CarStatus;
import com.example.rentacar.enums.FuelType;
import com.example.rentacar.enums.TransmissionType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CarResponse {
    Long id;
    String brand;
    String model;
    Integer year;
    BigDecimal pricePerDay;
    String color;
    String plateNumber;
    CarStatus status;
    FuelType fuelType;
    TransmissionType transmissionType;
    Integer seatingCapacity;
    String imageUrl;
    String description;
}