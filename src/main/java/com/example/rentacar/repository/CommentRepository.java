package com.example.rentacar.repository;

import com.example.rentacar.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    // Avtomobilin görünən şərhlərini tap
    List<Comment> findByCarIdAndIsVisibleTrue(Long carId);

    // İstifadəçinin bütün şərhlərini tap
    List<Comment> findByUserId(Long userId);

    // Admin paneli üçün: bütün şərhlər (gizlilər də daxil)
    List<Comment> findByCarId(Long carId);

    // İstifadəçinin bu avtomobilə şərh yazıb-yazmadığını yoxla
    boolean existsByUserIdAndCarId(Long userId, Long carId);

    // Avtomobilin ortalama reytinqini hesabla
    @Query("SELECT AVG(c.rating) FROM Comment c WHERE c.car.id = :carId AND c.isVisible = true")
    Double findAverageRatingByCarId(@Param("carId") Long carId);
}
