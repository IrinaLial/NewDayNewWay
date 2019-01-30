package com.trip.newway.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "stars")
    private int stars;

    @Column(name = "recommendations")
    private String recommendations;

}
