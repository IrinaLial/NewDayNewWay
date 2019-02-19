package com.trip.newway.dto.direction;

import com.trip.newway.model.Direction;
import com.trip.newway.model.Status;
import lombok.*;

/**
 * The {@link DirectionDTO} to read a {@link Direction } entity by controller.
 */

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DirectionDTO {
    private Long id;
    private String name;

}
