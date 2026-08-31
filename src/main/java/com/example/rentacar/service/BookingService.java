package com.example.rentacar.service;

import com.example.rentacar.enums.BookingStatus;
import com.example.rentacar.enums.PaymentStatus;
import com.example.rentacar.model.entity.Car;
import com.example.rentacar.model.request.BookingRequest;
import com.example.rentacar.model.response.BookingResponse;
import java.math.BigDecimal;
import java.util.List;

public interface BookingService {
    List<BookingResponse> getAllBookings();

    BookingResponse crateBooking(BookingRequest request, String username);

    List<BookingResponse> getMyBookings(String username);

    BookingResponse cancelMyBooking(Long bookingId, String username);

    BookingResponse updateBookingStatus(Long bookingId, BookingStatus status);

    BookingResponse updatePaymentStatus(Long bookingId, PaymentStatus paymentStatus);

    BigDecimal totalPrice(Car car, BookingRequest request);
}
