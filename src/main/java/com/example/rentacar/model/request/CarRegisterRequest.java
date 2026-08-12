package com.example.rentacar.model.request;

import com.example.rentacar.enums.FuelType;
import com.example.rentacar.enums.TransmissionType;
import jakarta.validation.constraints.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CarRegisterRequest {
    @NotBlank
    @Size(min = 1, max = 15)
    String brand;
    @NotBlank
    @Size(min = 1, max = 15)
    String color;
    @NotBlank
    @Size(min = 3, max = 12)
    String model;
    @NotNull
    @Min(2000)
    @Max(2026)
    Integer year;
    @NotNull
    @DecimalMax(value = "10000.00", message = "Qiymet cox yuksekdir")
    @DecimalMin(value = "30.00", message = "Qiymet cox azdir")
    BigDecimal pricePerDay;
    @NotBlank
    @Pattern(regexp = "^(0[1-9]|[1-8][0-9]|90|99)-[A-Z]{2}-[0-9]{3}$")
    String plateNumber;
    @NotNull
    FuelType fuelType;
    @NotNull
    TransmissionType transmissionType;
    @NotNull
    @Min(2)
    @Max(8)
    Integer seatingCapacity;
    @NotBlank
    String imageUrl;
    String description;

}
