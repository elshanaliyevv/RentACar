package com.example.rentacar.service;

import com.example.rentacar.enums.BookingStatus;
import com.example.rentacar.enums.PaymentStatus;
import com.example.rentacar.exception.BookingNotFoundException;
import com.example.rentacar.exception.CarIsNotAvailableException;
import com.example.rentacar.exception.CarNotFoundException;
import com.example.rentacar.exception.UserNotFoundException;
import com.example.rentacar.mapper.Mapper;
import com.example.rentacar.model.entity.Booking;
import com.example.rentacar.model.entity.Car;
import com.example.rentacar.model.entity.User;
import com.example.rentacar.model.request.BookingRequest;
import com.example.rentacar.model.response.BookingResponse;
import com.example.rentacar.repository.BookingRepository;
import com.example.rentacar.repository.CarRepository;
import com.example.rentacar.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final CarRepository carRepository;
    private final Mapper mapper;
    private final UserRepository userRepo;

    @Override
    public List<BookingResponse> getAllBookings() {
        return bookingRepository.findAll().stream()
                .map(bookings -> mapper.toBookingResponse(bookings, bookings.getCar()))
                .toList();
    }

    @Override
    public BookingResponse crateBooking(BookingRequest request, String username) {
        Optional<Car> optionalCar = carRepository.findById(request.getCarId());
        if (!optionalCar.isPresent()) {
            throw new CarNotFoundException("Bele bir masin movcud deyildir");
        }
        Car car = optionalCar.get();
        if (bookingRepository.existsOverlappingBooking(request.getCarId(), request.getStartDate(), request.getEndDate())) {
            throw new CarIsNotAvailableException("secdiyiniz gun masin icareye verilib");
        }
        Optional<User> optionalUser = userRepo.findByUsername(username);
        if (!optionalUser.isPresent()) {
            throw new UserNotFoundException("sizin hesab silinib");
        }
        User user = optionalUser.get();
        Booking booking = mapper.toBooking(request, car, user, totalPrice(car, request));

        return mapper.toBookingResponse(bookingRepository.save(booking), car);
    }

    @Override
    public List<BookingResponse> getMyBookings(String username) {
        Optional<User> optionalUser = userRepo.findByUsername(username);
        if (!optionalUser.isPresent()) {
            throw new UserNotFoundException("sizin hesab silinib");
        }
        List<Booking> bookings = bookingRepository.findByUserId(optionalUser.get().getId());
        return bookings.stream()
                .map(booking -> mapper.toBookingResponse(booking, booking.getCar()))
                .toList();
    }

    @Override
    public BookingResponse cancelMyBooking(Long bookingId, String username) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BookingNotFoundException("Booking tapilmadi"));
        if (!booking.getUser().getUsername().equals(username)) {
            throw new AccessDeniedException("bu booking size aid deyil");
        }
        booking.setBookingStatus(BookingStatus.CANCELLED);

        return mapper.toBookingResponse(bookingRepository.save(booking), booking.getCar());
    }

    @Override
    public BookingResponse updateBookingStatus(Long bookingId, BookingStatus status) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BookingNotFoundException("Booking tapilmadi"));
        booking.setBookingStatus(status);
        return mapper.toBookingResponse(bookingRepository.save(booking), booking.getCar());
    }

    @Override
    public BookingResponse updatePaymentStatus(Long bookingId, PaymentStatus paymentStatus) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BookingNotFoundException("Booking tapilmadi"));
        booking.setPaymentStatus(paymentStatus);
        return mapper.toBookingResponse(bookingRepository.save(booking), booking.getCar());
    }

    @Override
    public BigDecimal totalPrice(Car car, BookingRequest request) {
        long days = ChronoUnit.DAYS.between(request.getStartDate(), request.getEndDate());
        return car.getPricePerDay().multiply(BigDecimal.valueOf(days));
    }


}
