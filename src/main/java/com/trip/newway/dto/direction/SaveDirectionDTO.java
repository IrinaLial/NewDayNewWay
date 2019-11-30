package com.trip.newway.dto.direction;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveDirectionDTO {
    private Long placeIdFrom;
    private Long placeIdTo;
    private Double price;
    private String date;
    private Double estimateTime;
    private String message;
    private int countPlaces;
}
