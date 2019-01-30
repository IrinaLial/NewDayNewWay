package com.trip.newway.dto.review;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ResponseReviewDTO {
    private List<ReviewDTO> reviews;
    private Long total;
}
