package com.trip.newway.service.impl;

import com.trip.newway.dto.place.PlaceDTO;
import com.trip.newway.dto.place.ResponsePlaceDTO;
import com.trip.newway.dto.place.SavedPlaceDTO;
import com.trip.newway.model.Place;
import com.trip.newway.repository.PlaceReposirory;
import com.trip.newway.service.PlaceService;
import com.trip.newway.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.LinkedList;
import java.util.List;

@Service
public class PlaceServiceImpl implements PlaceService {
    @Autowired
    private PlaceReposirory placeReposirory;

    @Override
    public PlaceDTO save(SavedPlaceDTO placeDTO) {
        Assert.notNull(placeDTO,"place is null");
        Place place = new Place();
        place.setName(placeDTO.getName());
        place.setLatitude(placeDTO.getLatitude());
        place.setLongitude(placeDTO.getLongitude());

        Place savedPlace = placeReposirory.save(place);
        return new PlaceDTO(savedPlace.getId(),savedPlace.getName(),savedPlace.getLatitude(),savedPlace.getLongitude());
    }
    @Override
    public ResponsePlaceDTO findAll(int page) {
        List<Place> places = placeReposirory
                .findAll(PageRequest.of(page, Constants.LIMIT)).getContent();
        List<PlaceDTO> placeDTOS = new LinkedList<>();
        places.forEach(s->{
            PlaceDTO placeDTO = new PlaceDTO(s.getId(),s.getName(),s.getLatitude(),s.getLongitude());
            placeDTOS.add(placeDTO);
        });
        long count = placeReposirory.count();

        return new ResponsePlaceDTO(placeDTOS,count);
    }

    @Override
    public PlaceDTO findByName(String name) {
        return placeReposirory.findByName(name);
    }
}
//todo ask about "findByName"
