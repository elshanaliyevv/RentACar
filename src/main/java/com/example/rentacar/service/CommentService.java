package com.example.rentacar.service;

import com.example.rentacar.model.request.CommentRequest;
import com.example.rentacar.model.response.CommentResponse;

import java.util.List;

public interface CommentService {
    CommentResponse createComment(CommentRequest request, String username);

    void deleteComment(Long id, String username);

    List<CommentResponse> getCommentsByCar(Long carId);

    List<CommentResponse> getMyComments(String username);

    void toggleCommentVisibility(Long commentId, boolean isVisible);
}
