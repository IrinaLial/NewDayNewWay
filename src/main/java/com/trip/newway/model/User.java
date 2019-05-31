package com.trip.newway.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Getter
@Setter
@Entity
@Table(name = "users")
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

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "level_id")
    private Long levelId;

    @Column(name = "status_id")
    private int statusId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;

}