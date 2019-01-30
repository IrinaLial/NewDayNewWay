package com.trip.newway.dto.status;

import com.trip.newway.model.Status;
import lombok.*;

/**
 * The {@link StatusDTO} to read a {@link Status } entity by controller.
 */

@Getter
@Setter
@AllArgsConstructor
public class StatusDTO {
    private Long id;
    private int full;
    private int freePlace;
    private int empty;

}
