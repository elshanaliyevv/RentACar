package com.example.rentacar.model.entity;

import com.example.rentacar.enums.CarStatus;
import com.example.rentacar.enums.FuelType;
import com.example.rentacar.enums.TransmissionType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "cars")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    // Avtomobilin markası (məs: Toyota, BMW)
    @NotBlank
    @Column(nullable = false)
    String brand;

    // Avtomobilin modeli (məs: Camry, X5)
    @NotBlank
    @Column(nullable = false)
    String model;

    // Buraxılış ili
    @NotNull
    @Column(nullable = false)
    Integer year;

    // Gündəlik icarə qiyməti
    @NotNull
    @Positive
    @Column(name = "price_per_day", nullable = false, precision = 10, scale = 2)
    BigDecimal pricePerDay;

    // Avtomobilin rəngi
    @NotBlank
    @Column(nullable = false)
    String color;

    // Dövlət qeydiyyat nişanı (unikal)
    @NotBlank
    @Column(name = "plate_number", unique = true, nullable = false)
    String plateNumber;

    // Avtomobilin vəziyyəti (AVAILABLE, RENTED, MAINTENANCE)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    @Column(nullable = false)
    CarStatus status = CarStatus.AVAILABLE;

    // Yanacaq növü
    @Enumerated(EnumType.STRING)
    @Column(name = "fuel_type", nullable = false)
    FuelType fuelType;

    // Sürətlər qutusu
    @Enumerated(EnumType.STRING)
    @Column(name = "transmission_type", nullable = false)
    TransmissionType transmissionType;

    // Oturacaq sayı
    @Column(name = "seating_capacity", nullable = false)
    Integer seatingCapacity;

    // Şəkil URL-i
    @Column(name = "image_url")
    String imageUrl;

    // Avtomobil haqqında qısa məlumat
    @Column(columnDefinition = "TEXT")
    String description;

    // Ümumi yürüş (km)
    @Column(nullable = false)
    @Builder.Default
    Integer mileage = 0;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    LocalDateTime updatedAt;

    // Avtomobilə aid bütün rezervasiyalar
    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Booking> bookings;

    // Avtomobilə aid bütün şərhlər
    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Comment> comments;
}
