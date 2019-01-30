package com.trip.newway.dto.place;

import com.trip.newway.model.Place;
import lombok.*;

/**
 * The {@link PlaceDTO} to read a {@link Place } entity by controller.
 */

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PlaceDTO {
    private Long id;
    private String name;
    private double latitude;
    private double longitude;



}
