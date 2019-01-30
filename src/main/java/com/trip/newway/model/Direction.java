package com.trip.newway.model;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "direction")
public class Direction {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToOne
            (fetch = FetchType.EAGER)
    @JoinColumn(name="status_id",referencedColumnName = "id")
    private Status status;

//    @ManyToMany(fetch = FetchType.EAGER,
//            targetEntity = Direction.class,
//            cascade = {CascadeType.MERGE})
//    @JoinTable(name = "user_direction",
//            joinColumns = @JoinColumn(name = "direction_id"),
//            inverseJoinColumns = @JoinColumn(name = "user_id"))
//    private Set<User> user = new HashSet<>(0);


}
