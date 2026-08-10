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

    @NotBlank
    @Column(nullable = false)
    String brand;

    @NotBlank
    @Column(nullable = false)
    String model;

    @NotNull
    @Column(nullable = false)
    Integer year;

    @NotNull
    @Positive
    @Column(name = "price_per_day", nullable = false, precision = 10, scale = 2)
    BigDecimal pricePerDay;

    @NotBlank
    @Column(nullable = false)
    String color;

    @NotBlank
    @Column(name = "plate_number", unique = true, nullable = false)
    String plateNumber;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    @Column(nullable = false)
    CarStatus status = CarStatus.AVAILABLE;

    @Enumerated(EnumType.STRING)
    @Column(name = "fuel_type", nullable = false)
    FuelType fuelType;

    @Enumerated(EnumType.STRING)
    @Column(name = "transmission_type", nullable = false)
    TransmissionType transmissionType;

    @Column(name = "seating_capacity", nullable = false)
    Integer seatingCapacity;

    @Column(name = "image_url")
    String imageUrl;

    @Column(columnDefinition = "TEXT")
    String description;

    @Column(nullable = false)
    @Builder.Default
    Integer mileage = 0;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    LocalDateTime updatedAt;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Booking> bookings;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Comment> comments;
}
