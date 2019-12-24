package com.trip.newway.service;

import com.trip.newway.dto.NewDayNewWayResponse;
import com.trip.newway.dto.trips.FullTripDTO;
import com.trip.newway.dto.trips.ResponseTripDTO;
import com.trip.newway.dto.trips.SaveTripDTO;
import com.trip.newway.dto.trips.TripDTO;

import java.util.List;

public interface TripService {

    NewDayNewWayResponse save(SaveTripDTO saveTripDTO);

    /**
     * Apply user for trip.
     *
     * @param userId is user's identifier.
     * @param id is direction's identifier.
     */
    boolean applyUser(Long userId, Long id);

    boolean cancelTrip(Long id);

    FullTripDTO findWithId(Long id);

    List<TripDTO> findWithUserId(Long userId, int page);

    ResponseTripDTO findAll(int page);

    NewDayNewWayResponse deleteById(Long id);
}
