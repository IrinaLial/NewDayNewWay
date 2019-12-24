package com.trip.newway.dto.trips;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveTripDTO {
    private Double placeFromCoordinate;
    private Double placeToCoordinate;
    private String placeFromName;
    private String placeToName;
    private Double price;
    private String date;
    private Double estimateTime;
    private String message;
    private int countPlaces;
}
