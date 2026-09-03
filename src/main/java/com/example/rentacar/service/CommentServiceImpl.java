package com.example.rentacar.service;

import com.example.rentacar.exception.CarNotFoundException;
import com.example.rentacar.exception.CommentNotFoundException;
import com.example.rentacar.exception.UserNotFoundException;
import com.example.rentacar.mapper.Mapper;
import com.example.rentacar.model.entity.Car;
import com.example.rentacar.model.entity.Comment;
import com.example.rentacar.model.entity.User;
import com.example.rentacar.model.request.CommentRequest;
import com.example.rentacar.model.response.CommentResponse;
import com.example.rentacar.repository.BookingRepository;
import com.example.rentacar.repository.CarRepository;
import com.example.rentacar.repository.CommentRepository;
import com.example.rentacar.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.security.access.AccessDeniedException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final CarRepository carRepository;
    private final Mapper mapper;

    @Override
    public CommentResponse createComment(CommentRequest request, String username) {
        Car car = carRepository.findById(request.getCarId())
                .orElseThrow(() -> new CarNotFoundException("Bu" + request.getCarId() + "id ye mexsus masin yoxdur "));
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User tapilmadi"));
        if (!bookingRepository.existsByUserIdAndCarId(user.getId(), request.getCarId())) {
            throw new AccessDeniedException("Bu masini booking etmemisiniz");
        }
        return mapper.toCommentResponse(commentRepository.save(mapper.toComment(request, car, user)), car, user);
    }

    @Override
    public void deleteComment(Long id, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User tapilmadi"));
        if (!commentRepository.existsById(id)) {
            throw new CommentNotFoundException("Comment tapilmadi");
        }
        if (!commentRepository.existsByIdAndUserId(id, user.getId())) {
            throw new AccessDeniedException("qadagan olundu");
        }
        commentRepository.deleteById(id);
    }

    @Override
    public List<CommentResponse> getCommentsByCar(Long carId) {
        if (!carRepository.existsById(carId)) {
            throw new CarNotFoundException("masin tapilmadi");
        }
        return commentRepository.findByCarIdAndIsVisibleTrue(carId)
                .stream()
                .map(comment -> mapper.toCommentResponse(comment, comment.getCar(), comment.getUser()))
                .toList();
    }

    @Override
    public List<CommentResponse> getMyComments(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User tapilmadi"));
        return commentRepository.findByUserId(user.getId())
                .stream()
                .map(comment -> mapper.toCommentResponse(comment, comment.getCar(), user))
                .toList();
    }

    @Override
    public void toggleCommentVisibility(Long commentId, boolean isVisible) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException("Comment tapilmadi"));
        comment.setIsVisible(isVisible);
        commentRepository.save(comment);
    }
}
