package com.trip.newway.dto.cars;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
public class CarDTO {
    private Long id;
    private String name;
    private int countPlaces;
}
