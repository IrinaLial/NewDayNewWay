package com.trip.newway.service;

import com.trip.newway.dto.review.ResponseReviewDTO;
import com.trip.newway.dto.review.ReviewDTO;
import com.trip.newway.dto.review.SavedReviewDTO;

public interface ReviewService {
    ReviewDTO save(SavedReviewDTO review);
    ResponseReviewDTO findAll(int page);
    ReviewDTO findByStars(int stars);
}
