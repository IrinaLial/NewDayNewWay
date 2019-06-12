package com.trip.newway.controller;

import com.trip.newway.dto.review.ResponseReviewDTO;
import com.trip.newway.dto.review.ReviewDTO;
import com.trip.newway.dto.review.SavedReviewDTO;
import com.trip.newway.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PreAuthorize(value = "hasAuthority('USER')")
    @PostMapping
    public ResponseEntity<ReviewDTO> save(@RequestBody SavedReviewDTO review) {
        ReviewDTO savedReviewDTO = reviewService.save(review);
        return new ResponseEntity<>(savedReviewDTO, HttpStatus.OK);
    }

    @PreAuthorize(value = "hasAuthority('ADMIN')")
    @GetMapping("/findAll")
    public ResponseEntity<ResponseReviewDTO> findAll(@RequestParam int page) {
        ResponseReviewDTO responseReviewDTO = reviewService.findAll(page);
        return new ResponseEntity<>(responseReviewDTO, HttpStatus.OK);
    }

    @GetMapping("/findByStars")
    public ResponseEntity<ReviewDTO> findByStars(@RequestParam int stars) {
        final ReviewDTO reviewDTO = reviewService.findByStars(stars);
        return new ResponseEntity<>(reviewDTO, HttpStatus.OK);

    }
    @DeleteMapping
    public ResponseEntity delete(@RequestParam Long id) {
        reviewService.deleteById(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
