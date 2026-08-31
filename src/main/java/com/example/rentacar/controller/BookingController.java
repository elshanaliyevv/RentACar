package com.example.rentacar.controller;

import com.example.rentacar.enums.BookingStatus;
import com.example.rentacar.enums.PaymentStatus;
import com.example.rentacar.model.request.BookingRequest;
import com.example.rentacar.model.response.BookingResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import com.example.rentacar.service.BookingServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("booking")
@RequiredArgsConstructor
public class BookingController {
    private final BookingServiceImpl service;

    @GetMapping("/getall")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<BookingResponse>> getAllBookings() {
        return ResponseEntity.ok(service.getAllBookings());
    }

    @GetMapping("/my")
    public ResponseEntity<List<BookingResponse>> getMyBookings(Authentication authentication) {
        String username = authentication.getName();
        return ResponseEntity.ok(service.getMyBookings(username));
    }

    @PostMapping("/create")
    public ResponseEntity<BookingResponse> createBooking(@RequestBody @Valid BookingRequest bookingRequest, Authentication authentication) {
        String username = authentication.getName();
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crateBooking(bookingRequest, username));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookingResponse> cancelBooking(@PathVariable Long id, Authentication authentication) {
        String username = authentication.getName();
        return ResponseEntity.ok(service.cancelMyBooking(id,username));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/bookingstatus/{id}")
    public ResponseEntity<BookingResponse> updateBookingStatus(@PathVariable Long id, @RequestParam BookingStatus status){
        return ResponseEntity.ok(service.updateBookingStatus(id,status));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/paymentstatus/{id}")
    public ResponseEntity<BookingResponse> updatePaymentMethod(@PathVariable Long id, @RequestParam PaymentStatus status){
        return ResponseEntity.ok(service.updatePaymentStatus(id,status));
    }
}
