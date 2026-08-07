package com.example.rentacar.model.entity;

import com.example.rentacar.enums.PaymentMethod;
import com.example.rentacar.enums.PaymentStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "payments")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    // Bu ödəniş hansı rezervasiyaya aiddir
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id", nullable = false, unique = true)
    Booking booking;

    // Ödənilən məbləğ
    @NotNull
    @Positive
    @Column(nullable = false, precision = 10, scale = 2)
    BigDecimal amount;

    // Ödəniş üsulu
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", nullable = false)
    PaymentMethod paymentMethod;

    // Ödənişin vəziyyəti
    @Enumerated(EnumType.STRING)
    @Builder.Default
    @Column(name = "payment_status", nullable = false)
    PaymentStatus paymentStatus = PaymentStatus.PENDING;

    // Xarici ödəniş sistemi tərəfindən qaytarılan tranzaksiya ID-si
    @Column(name = "transaction_id", unique = true)
    String transactionId;

    // Ödənişin həyata keçirildiyi vaxt
    @Column(name = "paid_at")
    LocalDateTime paidAt;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    LocalDateTime createdAt;
}
