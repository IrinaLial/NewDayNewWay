package com.trip.newway.service;

import com.trip.newway.dto.NewDayNewWayResponse;
import com.trip.newway.dto.cars.CarDTO;
import com.trip.newway.dto.cars.ResponseCarDTO;
import com.trip.newway.dto.cars.SaveCarDTO;

import java.util.List;

/**
 * @author Igor Hnes on 24.12.2019.
 */
public interface CarService {

    CarDTO save(SaveCarDTO saveCarDTO);

    ResponseCarDTO findAll(int page);

    List<CarDTO> findWithUserId(Long userId);

    NewDayNewWayResponse deleteById(Long id);
}
