package com.example.rentacar.repository;

import com.example.rentacar.enums.CarStatus;
import com.example.rentacar.model.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> findByStatus(CarStatus status);

    List<Car> findByBrandIgnoreCase(String brand);

    boolean existsByPlateNumber(String plateNumber);

    List<Car> findByBrandIgnoreCaseAndModelIgnoreCase(String brand, String model);
}
