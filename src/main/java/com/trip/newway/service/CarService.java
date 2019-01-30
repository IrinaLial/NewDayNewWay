package com.trip.newway.service;

import com.trip.newway.dto.car.CarDTO;
import com.trip.newway.dto.car.ResponseCarDTO;
import com.trip.newway.dto.car.SavedCarDTO;

public interface CarService {

    CarDTO save (SavedCarDTO car);

    ResponseCarDTO findAll (int page);

}
