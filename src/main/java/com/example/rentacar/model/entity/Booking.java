package com.example.rentacar.model.entity;

import com.example.rentacar.enums.BookingStatus;
import com.example.rentacar.enums.PaymentStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "bookings")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    // Rezervasiyanı edən istifadəçi
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    // İcarəyə götürülən avtomobil
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id", nullable = false)
    Car car;

    // İcarənin başlanğıc tarixi
    @NotNull
    @Column(name = "start_date", nullable = false)
    LocalDate startDate;

    // İcarənin bitmə tarixi
    @NotNull
    @Column(name = "end_date", nullable = false)
    LocalDate endDate;

    // Ümumi məbləğ (gün sayı * gündəlik qiymət)
    @Column(name = "total_price", nullable = false, precision = 10, scale = 2)
    BigDecimal totalPrice;

    // Rezervasiyanın vəziyyəti (PENDING, CONFIRMED, CANCELLED, COMPLETED)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    @Column(name = "booking_status", nullable = false)
    BookingStatus bookingStatus = BookingStatus.PENDING;

    // Ödəniş vəziyyəti
    @Enumerated(EnumType.STRING)
    @Builder.Default
    @Column(name = "payment_status", nullable = false)
    PaymentStatus paymentStatus = PaymentStatus.PENDING;

    // Əlavə qeydlər / xüsusi tələblər
    @Column(columnDefinition = "TEXT")
    String notes;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    LocalDateTime updatedAt;

    // Bu rezervasiyaya aid ödəniş
    @OneToOne(mappedBy = "booking", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Payment payment;
}
