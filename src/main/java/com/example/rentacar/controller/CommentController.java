package com.example.rentacar.controller;

import com.example.rentacar.model.request.CommentRequest;
import com.example.rentacar.model.response.CommentResponse;
import com.example.rentacar.service.CommentServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("comment")
public class CommentController {
    private final CommentServiceImpl service;

    @PostMapping("/create")
    public ResponseEntity<CommentResponse> createComment(@RequestBody @Valid CommentRequest request, Authentication authentication) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createComment(request, authentication.getName()));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id, Authentication authentication) {
        service.deleteComment(id, authentication.getName());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/car/{id}")
    public ResponseEntity<List<CommentResponse>> getByCarId(@PathVariable Long id) {
        return ResponseEntity.ok(service.getCommentsByCar(id));
    }

    @GetMapping("/my")
    public ResponseEntity<List<CommentResponse>> getMyComments(Authentication authentication){
        return ResponseEntity.ok(service.getMyComments(authentication.getName()));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/visibility/{id}")
    public ResponseEntity<Void> changeVisibility(@PathVariable Long id,@RequestParam boolean visible){
        service.toggleCommentVisibility(id,visible);
        return ResponseEntity.ok().build();
    }
}
