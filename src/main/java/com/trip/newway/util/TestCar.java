package com.trip.newway.util;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "test_car")
public class TestCar {
    @Column(name = "type")
    private String type;
}
