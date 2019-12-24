package com.trip.newway.dto.cars;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ResponseCarDTO {
    private List<CarDTO> car;
    private long total;
}
