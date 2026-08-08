package com.example.rentacar.repository;

import com.example.rentacar.enums.CarStatus;
import com.example.rentacar.model.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    // Statusuna görə avtomobilləri tap
    List<Car> findByStatus(CarStatus status);

    // Markaya görə avtomobilləri tap
    List<Car> findByBrandIgnoreCase(String brand);

    // Qeydiyyat nişanına görə avtomobil tap
    boolean existsByPlateNumber(String plateNumber);

    // Markası VƏ modeli üzrə axtar
    List<Car> findByBrandIgnoreCaseAndModelIgnoreCase(String brand, String model);
}
