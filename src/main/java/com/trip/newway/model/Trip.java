package com.trip.newway.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "trips")
public class Trip {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "status")
    private int status;

    @Column(name = "user_id")
    private Long userId;

    @Column( name = "place_from_coordinate")
    private Double placeFromCoordinate;

    @Column( name = "place_to_coordinate")
    private Double placeToCoordinate;

    @Column( name = "place_from")
    private String placeFrom;

    @Column( name = "place_to")
    private String placeTo;

    @Column( name = "price")
    private Double price;

    @Column( name = "estimate_time")
    private Double estimateTime;

    @Column( name = "date")
    private LocalDateTime date;

    @Column( name = "message")
    private String message;

    @Column( name = "count_places")
    private int countPlaces;

    @Column( name = "created_at")
    private LocalDateTime createdAt;
}
