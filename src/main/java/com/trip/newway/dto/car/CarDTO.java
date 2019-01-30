package com.trip.newway.dto.car;

import com.trip.newway.model.Car;
import lombok.*;

/**
 * The {@link CarDTO} to read a {@link Car } entity by controller.
 */

@Getter
@Setter
@AllArgsConstructor
public class CarDTO {
    private Long id;
    private String name;
}
