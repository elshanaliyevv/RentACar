package com.example.rentacar.repository;

import com.example.rentacar.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByCarIdAndIsVisibleTrue(Long carId);

    List<Comment> findByUserId(Long userId);

    List<Comment> findByCarId(Long carId);

    boolean existsByUserIdAndCarId(Long userId, Long carId);

    @Query("SELECT AVG(c.rating) FROM Comment c WHERE c.car.id = :carId AND c.isVisible = true")
    Double findAverageRatingByCarId(@Param("carId") Long carId);

    boolean existsByIdAndUserId(Long id, Long user_id);
}
