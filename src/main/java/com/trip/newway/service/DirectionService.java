package com.trip.newway.service;

import com.trip.newway.dto.direction.DirectionDTO;
import com.trip.newway.dto.direction.ResponseDirectionDTO;
import com.trip.newway.dto.direction.SavedDirectionDTO;


public interface DirectionService {

    DirectionDTO save(SavedDirectionDTO direction);

    ResponseDirectionDTO findAll(int page);

    DirectionDTO findByName(String name);
}
