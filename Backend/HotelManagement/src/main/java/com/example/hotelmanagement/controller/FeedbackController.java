package com.example.hotelmanagement.controller;

import com.example.hotelmanagement.model.Feedback;
import com.example.hotelmanagement.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/feedbacks")
@CrossOrigin(origins = "http://localhost:3000")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping
    public ResponseEntity<?> addFeedback(@RequestBody Feedback feedback) {
        Feedback createdFeedback = feedbackService.addFeedback(feedback);
        return ResponseEntity.ok(createdFeedback);
    }

    @GetMapping("/average/{hotelId}")
    public ResponseEntity<Double> getAverageRating(@PathVariable Long hotelId) {
        double averageRating = feedbackService.getAverageRating(hotelId);
        return ResponseEntity.ok(averageRating);
    }
}