package com.example.hotelmanagement.service;

import com.example.hotelmanagement.model.Feedback;
import com.example.hotelmanagement.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    public Feedback addFeedback(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    public double getAverageRating(Long hotelId) {
        List<Feedback> feedbacks = feedbackRepository.findByHotelId(hotelId);
        return feedbacks.stream()
                .mapToInt(Feedback::getRating)
                .average()
                .orElse(0.0);
    }
}

