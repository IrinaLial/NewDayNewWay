package com.trip.newway.service;

import com.trip.newway.dto.direction.DirectionDTO;
import com.trip.newway.dto.direction.ResponseDirectionDTO;
import com.trip.newway.dto.direction.SavedDirectionDTO;
import com.trip.newway.model.Direction;

import java.util.List;


public interface DirectionService {

    DirectionDTO save(SavedDirectionDTO direction);

    ResponseDirectionDTO findAll(int page);

    DirectionDTO findByName(String name);

    List<Direction> findByUserId(Long userId, int page);
}
