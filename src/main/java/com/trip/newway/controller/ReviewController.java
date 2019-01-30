package com.trip.newway.controller;

import com.trip.newway.dto.review.ResponseReviewDTO;
import com.trip.newway.dto.review.ReviewDTO;
import com.trip.newway.dto.review.SavedReviewDTO;
import com.trip.newway.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ReviewDTO> save(@RequestBody SavedReviewDTO review){
        ReviewDTO savedReviewDTO = reviewService.save(review);
        return new ResponseEntity<>(savedReviewDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ResponseReviewDTO> findAll(@RequestParam int page){
        ResponseReviewDTO responseReviewDTO = reviewService.findAll(page);
        return new ResponseEntity<>(responseReviewDTO,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ReviewDTO>findByStars (@RequestParam int stars){
        final ReviewDTO reviewDTO = reviewService.findByStars(stars);
        return new ResponseEntity<>(reviewDTO,HttpStatus.OK);

    }
}
