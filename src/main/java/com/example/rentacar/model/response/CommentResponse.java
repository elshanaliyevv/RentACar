package com.example.rentacar.model.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentResponse {
    Long id;
    String carBrand;
    String carModel;
    String username;
    Integer rating;
    String comment;
    LocalDateTime createdAt;
}
