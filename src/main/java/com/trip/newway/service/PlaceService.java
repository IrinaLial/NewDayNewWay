package com.trip.newway.service;

import com.trip.newway.dto.place.PlaceDTO;
import com.trip.newway.dto.place.ResponsePlaceDTO;
import com.trip.newway.dto.place.SavedPlaceDTO;

public interface PlaceService {
    PlaceDTO save(SavedPlaceDTO place);

    ResponsePlaceDTO findAll(int page);

    PlaceDTO findByName(String name);

    void deleteById(Long id);
}
