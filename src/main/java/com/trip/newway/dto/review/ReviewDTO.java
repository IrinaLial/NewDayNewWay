package com.trip.newway.dto.review;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
public class ReviewDTO {
    private Long id;
    private Integer stars;
    private String recommendations;
    private String user;
}
