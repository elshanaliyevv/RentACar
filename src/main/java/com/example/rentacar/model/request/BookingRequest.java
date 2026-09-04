package com.example.rentacar.model.request;

import com.example.rentacar.enums.PaymentMethod;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingRequest {
    @NotNull
    Long carId;
    @NotNull
    @FutureOrPresent
    LocalDate startDate;
    @NotNull
    @FutureOrPresent
    LocalDate endDate;
    @NotNull
    PaymentMethod paymentMethod;
    String notes;
}
