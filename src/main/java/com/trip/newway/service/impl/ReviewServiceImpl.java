package com.trip.newway.service.impl;

import com.trip.newway.dto.review.ResponseReviewDTO;
import com.trip.newway.dto.review.ReviewDTO;
import com.trip.newway.dto.review.SavedReviewDTO;
import com.trip.newway.model.Review;
import com.trip.newway.repository.ReviewRepository;
import com.trip.newway.service.ReviewService;
import com.trip.newway.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.LinkedList;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public ReviewDTO save(SavedReviewDTO reviewDTO) {
        Assert.notNull(reviewDTO,"place is null");
        Review review = new Review();
        review.setStars(reviewDTO.getStars());
        review.setRecommendations(reviewDTO.getRecommendations());

        Review savedReview = reviewRepository.save(review);
        return new ReviewDTO(savedReview.getId(),savedReview.getStars(),savedReview.getRecommendations());
    }

    @Override
    public ResponseReviewDTO findAll(int page) {
        List<Review> reviews = reviewRepository
                .findAll(PageRequest.of(page, Constants.LIMIT)).getContent();
        List<ReviewDTO> reviewDTOS = new LinkedList<>();
        reviews.forEach(s->{
            ReviewDTO placeDTO = new ReviewDTO(s.getId(),s.getStars(),s.getRecommendations());
            reviewDTOS.add(placeDTO);
        });
        long count = reviewRepository.count();

        return new ResponseReviewDTO(reviewDTOS,count);
    }

    @Override
    public ReviewDTO findByStars(int stars) {
        return reviewRepository.findByStars(stars);
    }
}
//todo ask about "findByStars"