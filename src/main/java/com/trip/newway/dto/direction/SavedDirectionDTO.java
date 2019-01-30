package com.trip.newway.dto.direction;

import com.trip.newway.model.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SavedDirectionDTO {
    private String name;
    private Status status;

}
