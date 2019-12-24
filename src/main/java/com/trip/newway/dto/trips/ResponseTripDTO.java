package com.trip.newway.dto.trips;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ResponseTripDTO {
    List<TripDTO> trips;
    private long count;
}
