package com.example.rentacar.repository;

import com.example.rentacar.enums.BookingStatus;
import com.example.rentacar.model.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByUserId(Long userId);

    List<Booking> findByCarId(Long carId);

    List<Booking> findByBookingStatus(BookingStatus status);

    List<Booking> findByUserIdAndBookingStatus(Long userId, BookingStatus status);

    @Query("SELECT COUNT(b) > 0 FROM Booking b WHERE b.car.id = :carId " +
           "AND b.bookingStatus NOT IN ('CANCELLED') " +
           "AND b.startDate < :endDate AND b.endDate > :startDate")
    boolean existsOverlappingBooking(@Param("carId") Long carId,
                                     @Param("startDate") LocalDate startDate,
                                     @Param("endDate") LocalDate endDate);
}
