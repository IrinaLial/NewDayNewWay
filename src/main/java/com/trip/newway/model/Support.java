package com.trip.newway.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "support")
public class Support {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "subject")
    private String subject;

    @Column(name = "text")
    private String text;

    @OneToOne
            (fetch = FetchType.EAGER)
    @JoinColumn(name="user_id",referencedColumnName = "id")
    private User user;


}
