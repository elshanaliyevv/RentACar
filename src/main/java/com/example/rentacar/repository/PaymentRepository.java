package com.example.rentacar.repository;

import com.example.rentacar.enums.PaymentStatus;
import com.example.rentacar.model.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    // Rezervasiyaya aid ödənişi tap
    Optional<Payment> findByBookingId(Long bookingId);

    // Tranzaksiya ID-sinə görə tap
    Optional<Payment> findByTransactionId(String transactionId);

    // Statusuna görə ödənişləri tap
    List<Payment> findByPaymentStatus(PaymentStatus paymentStatus);

    // Tranzaksiya ID mövcuddurmu?
    boolean existsByTransactionId(String transactionId);
}
