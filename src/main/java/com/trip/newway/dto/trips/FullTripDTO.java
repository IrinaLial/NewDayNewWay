package com.trip.newway.dto.trips;

import com.trip.newway.model.Trip;
import com.trip.newway.util.Constants;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The {@link FullTripDTO} to read a {@link Trip } entity by controller.
 */

@Getter
@Setter
@NoArgsConstructor
public class FullTripDTO {
    private Long id;
    private int places;
    private Double estimateTime;
    private Double placeFromCoordinate;
    private Double placeToCoordinate;
    private String placeFromName;
    private String placeToName;
    private Double price;
    private String message;
    private String date;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.DATE_FORMAT);

    public FullTripDTO(Long id, int places, Double estimateTime, Double placeFromCoordinate, Double placeToCoordinate, String placeFromName, String placeToName, Double price, String message, LocalDateTime date) {
        this.id = id;
        this.places = places;
        this.estimateTime = estimateTime;
        this.placeFromCoordinate = placeFromCoordinate;
        this.placeToCoordinate = placeToCoordinate;
        this.placeFromName = placeFromName;
        this.placeToName = placeToName;
        this.price = price;
        this.message = message;
        this.date = formatter.format(date);
    }
}
