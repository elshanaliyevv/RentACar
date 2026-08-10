package com.example.rentacar.repository;

import com.example.rentacar.enums.PaymentStatus;
import com.example.rentacar.model.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Optional<Payment> findByBookingId(Long bookingId);

    Optional<Payment> findByTransactionId(String transactionId);

    List<Payment> findByPaymentStatus(PaymentStatus paymentStatus);

    boolean existsByTransactionId(String transactionId);
}
