package com.trip.newway.dto.direction;

import com.trip.newway.model.Direction;
import com.trip.newway.model.Status;
import com.trip.newway.util.Constants;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The {@link DirectionDTO} to read a {@link Direction } entity by controller.
 */

@Getter
@Setter
@ToString
@NoArgsConstructor
public class DirectionDTO {
    private Long id;
    private String from;
    private String to;
    private String date;
    private int freePlaces;
    private Double estimateTime;

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(Constants.DATE_FORMAT);

    public DirectionDTO(Long id, String from, String to, LocalDateTime date, int freePlaces, Double estimateTime) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.date = dateTimeFormatter.format(date);
        this.freePlaces = freePlaces;
        this.estimateTime = estimateTime;
    }
}