package com.trip.newway.dto.direction;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ResponseDirectionDTO {
    List<DirectionDTO> directions;
    private long total;
}
