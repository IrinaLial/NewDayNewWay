package com.trip.newway.repository;

import com.trip.newway.dto.review.ReviewDTO;
import com.trip.newway.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("select new com.trip.newway.dto.review.ReviewDTO (r.id, r.stars,r.recommendations) from Review r where r.stars = :stars")
    ReviewDTO findByStars(@Param("stars") Integer stars);
}
