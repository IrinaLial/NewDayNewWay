package com.trip.newway.service;

import com.trip.newway.dto.car.CarDTO;
import com.trip.newway.dto.car.ResponseCarDTO;
import com.trip.newway.dto.car.SavedCarDTO;
import com.trip.newway.model.Car;

import java.util.List;

public interface CarService {

    CarDTO save(SavedCarDTO car);

    ResponseCarDTO findAll(int page);

    List<Car> findByUserId(Long userId);

}
