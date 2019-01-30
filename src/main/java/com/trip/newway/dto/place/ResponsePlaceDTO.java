package com.trip.newway.dto.place;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ResponsePlaceDTO {
    private List<PlaceDTO> places;
    private long total;

}
