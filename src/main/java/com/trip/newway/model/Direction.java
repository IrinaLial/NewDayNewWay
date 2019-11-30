package com.trip.newway.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "directions")
public class Direction {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "status_id")
    private int statusId;

    @Column(name = "user_id")
    private Long userId;

    @Column( name = "active")
    private Boolean active;

    @Column( name = "place_id_from")
    private Long placeIdFrom;

    @Column( name = "place_id_to")
    private Long placeIdTo;

    @Column( name = "price")
    private Double price;

    @Column( name = "date")
    private LocalDateTime date;

    @Column( name = "estimate_time")
    private Double estimateTime;

    @Column( name = "message")
    private String message;

    @Column( name = "count_places")
    private int countPlaces;

    @Column( name = "created_at")
    private LocalDateTime createdAt;


}
