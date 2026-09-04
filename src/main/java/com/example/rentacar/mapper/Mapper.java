package com.example.rentacar.mapper;

import com.example.rentacar.enums.Roles;
import com.example.rentacar.model.entity.Booking;
import com.example.rentacar.model.entity.Car;
import com.example.rentacar.model.entity.Comment;
import com.example.rentacar.model.entity.User;
import com.example.rentacar.model.request.BookingRequest;
import com.example.rentacar.model.request.CarRegisterRequest;
import com.example.rentacar.model.request.CommentRequest;
import com.example.rentacar.model.request.UserRegisterRequest;
import com.example.rentacar.model.response.BookingResponse;
import com.example.rentacar.model.response.CarResponse;
import com.example.rentacar.model.response.CommentResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class Mapper {
    public User toUser(UserRegisterRequest userRegisterRequest) {
        return User.builder()
                .username(userRegisterRequest.getUsername())
                .email(userRegisterRequest.getEmail())
                .number(userRegisterRequest.getNumber())
                .role(Roles.USER)
                .isActive(true)
                .build();
    }

    public Car toCar(CarRegisterRequest carRegisterRequest) {
        return Car.builder()
                .brand(carRegisterRequest.getBrand())
                .color(carRegisterRequest.getColor())
                .description(carRegisterRequest.getDescription())
                .fuelType(carRegisterRequest.getFuelType())
                .model(carRegisterRequest.getModel())
                .plateNumber(carRegisterRequest.getPlateNumber())
                .pricePerDay(carRegisterRequest.getPricePerDay())
                .imageUrl(carRegisterRequest.getImageUrl())
                .transmissionType(carRegisterRequest.getTransmissionType())
                .year(carRegisterRequest.getYear())
                .mileage(carRegisterRequest.getMileage())
                .seatingCapacity(carRegisterRequest.getSeatingCapacity())
                .build();
    }

    public CarResponse toCarResponse(Car car) {
        return CarResponse.builder()
                .id(car.getId())
                .brand(car.getBrand())
                .color(car.getColor())
                .description(car.getDescription())
                .fuelType(car.getFuelType())
                .imageUrl(car.getImageUrl())
                .model(car.getModel())
                .plateNumber(car.getPlateNumber())
                .pricePerDay(car.getPricePerDay())
                .seatingCapacity(car.getSeatingCapacity())
                .transmissionType(car.getTransmissionType())
                .year(car.getYear())
                .mileage(car.getMileage())
                .status(car.getStatus())
                .build();

    }

    public Booking toBooking(BookingRequest request, Car car, User user, BigDecimal totalPrice) {
        return Booking.builder()
                .car(car)
                .user(user)
                .totalPrice(totalPrice)
                .startDate(request.getStartDate())
                .paymentMethod(request.getPaymentMethod())
                .endDate(request.getEndDate())
                .notes(request.getNotes())
                .build();
    }

    public BookingResponse toBookingResponse(Booking booking, Car car) {
        return BookingResponse.builder()
                .bookingStatus(booking.getBookingStatus())
                .carBrand(car.getBrand())
                .carModel(car.getModel())
                .carPlateNumber(car.getPlateNumber())
                .endDate(booking.getEndDate())
                .startDate(booking.getStartDate())
                .notes(booking.getNotes())
                .id(booking.getId())
                .totalPrice(booking.getTotalPrice())
                .paymentMethod(booking.getPaymentMethod())
                .paymentStatus(booking.getPaymentStatus())
                .build();

    }

    public Comment toComment(CommentRequest request, Car car, User user) {
        return Comment.builder()
                .car(car)
                .user(user)
                .rating(request.getRating())
                .content(request.getComment())
                .build();
    }

    public CommentResponse toCommentResponse(Comment comment, Car car, User user) {
        return CommentResponse.builder()
                .id(comment.getId())
                .carBrand(car.getBrand())
                .carModel(car.getModel())
                .rating(comment.getRating())
                .comment(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .username(user.getUsername())
                .build();
    }
}
