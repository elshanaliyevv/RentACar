package com.example.rentacar.model.response;

import com.example.rentacar.enums.BookingStatus;
import com.example.rentacar.enums.PaymentMethod;
import com.example.rentacar.enums.PaymentStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingResponse {
    Long id;
    String carBrand;
    String carModel;
    String carPlateNumber;
    LocalDate startDate;
    LocalDate endDate;
    BigDecimal totalPrice;
    BookingStatus bookingStatus;
    PaymentMethod paymentMethod;
    PaymentStatus paymentStatus;
    String notes;
}
