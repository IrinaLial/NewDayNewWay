package com.trip.newway.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @Column(name = "name")
    public String name;
    @Column(name = "email")
    @Email
    public String email;

    @Column(name = "password")
    public String password;

    //todo CONSTANTS CREATE

    @Column(name = "is_active")
    private boolean isActive;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_direction", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "direction_id"))
    private List<Direction> directions;
    //todo (ask about status_id)


    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="level_id",referencedColumnName = "id")
    private Level level;

//    @OneToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name="car_id",referencedColumnName = "id")
//    private Car car;
}
