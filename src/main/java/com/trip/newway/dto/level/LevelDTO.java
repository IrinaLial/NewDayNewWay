package com.trip.newway.dto.level;

import com.trip.newway.model.Level;
import lombok.*;

/**
 * The {@link LevelDTO} to read a {@link Level } entity by controller.
 */

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LevelDTO {
    private Long id;
    private String name;
}
