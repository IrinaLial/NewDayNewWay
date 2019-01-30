package com.trip.newway.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "status")
public class Status {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "full")
    private int full;

    @Column(name = "empty")
    private int empty;

    @Column(name = "free_place")
    private int freePlace;
}
