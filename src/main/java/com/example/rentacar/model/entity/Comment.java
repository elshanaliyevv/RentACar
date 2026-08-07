package com.example.rentacar.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "comments")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    // Şərhi yazan istifadəçi
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    // Şərhin aid olduğu avtomobil
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id", nullable = false)
    Car car;

    // Şərhin mətni
    @NotBlank
    @Column(nullable = false, columnDefinition = "TEXT")
    String content;

    // Reytinq: 1-dən 5-ə qədər
    @NotNull
    @Min(1)
    @Max(5)
    @Column(nullable = false)
    Integer rating;

    // Admin tərəfindən silinib/gizlədilib?
    @Builder.Default
    @Column(name = "is_visible")
    Boolean isVisible = true;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    LocalDateTime updatedAt;
}
