package com.trip.newway.service;

import com.trip.newway.dto.NewDayNewWayResponse;
import com.trip.newway.dto.direction.DirectionDTO;
import com.trip.newway.dto.direction.ResponseDirectionDTO;
import com.trip.newway.dto.direction.SaveDirectionDTO;
import com.trip.newway.dto.direction.SavedDirectionDTO;
import com.trip.newway.model.Direction;

import java.util.List;


public interface DirectionService {

    NewDayNewWayResponse save(SaveDirectionDTO saveDirectionDTO);


    ResponseDirectionDTO findAll(int page);

    /**
     * Apply user for trip.
     *
     * @param userId is user's identifier.
     * @param id is direction's identifier.
     */
    boolean applyUser(Long userId, Long id);

    DirectionDTO findByName(String name);

    List<DirectionDTO> findWithUserId(Long userId, int page);

    boolean cancelTrip(Long id);

    void deleteById(Long id);

}
