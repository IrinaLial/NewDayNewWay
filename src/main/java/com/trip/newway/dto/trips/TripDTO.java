package com.trip.newway.dto.trips;

import com.trip.newway.util.Constants;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Igor Hnes on 24.12.2019.
 */
@Getter
@Setter
public class TripDTO {
    private Long id;
    private String placeFrom;
    private String placeTo;
    private Double price;
    private String date;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.DATE_FORMAT);

    public TripDTO(Long id, String placeFrom, String placeTo, Double price, LocalDateTime date) {
        this.id = id;
        this.placeFrom = placeFrom;
        this.placeTo = placeTo;
        this.price = price;
        this.date = formatter.format(date);
    }
}
