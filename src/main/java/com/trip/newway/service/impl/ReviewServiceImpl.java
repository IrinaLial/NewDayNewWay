package com.trip.newway.service.impl;

import com.sun.tools.internal.ws.wsdl.framework.NoSuchEntityException;
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

import static org.springframework.util.Assert.notNull;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public ReviewDTO save(SavedReviewDTO reviewDTO) {
        Assert.notNull(reviewDTO, "place is null");
        Review review = new Review();
        review.setStars(reviewDTO.getStars());
        review.setRecommendations(reviewDTO.getRecommendations());

        Review savedReview = reviewRepository.save(review);
        return new ReviewDTO(savedReview.getId(), savedReview.getStars(), savedReview.getRecommendations());
    }

    @Override
    public ResponseReviewDTO findAll(int page) {
        if( page < 0){
            return new ResponseReviewDTO(new LinkedList<>(),0);
        }
        List<ReviewDTO> reviews = reviewRepository
                .findReviews(PageRequest.of(page, Constants.LIMIT)).getContent();
        long count = reviewRepository.count();

        return new ResponseReviewDTO(reviews, count);
    }

    @Override
    public ReviewDTO findByStars(int stars) {
        return reviewRepository.findByStars(stars);
    }

    @Override
    public void deleteById(Long id) {
        notNull(id, "id is null");
        final Review review = reviewRepository.findById(id)
                .orElseThrow(()->  new NoSuchEntityException("id isn`t found" + id));
        reviewRepository.deleteById(review.getId());
    }
}
