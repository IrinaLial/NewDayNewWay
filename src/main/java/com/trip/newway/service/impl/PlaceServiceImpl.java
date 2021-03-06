package com.trip.newway.service.impl;

import com.sun.tools.internal.ws.wsdl.framework.NoSuchEntityException;
import com.trip.newway.dto.place.PlaceDTO;
import com.trip.newway.dto.place.ResponsePlaceDTO;
import com.trip.newway.dto.place.SavedPlaceDTO;
import com.trip.newway.model.Place;
import com.trip.newway.repository.PlaceRepository;
import com.trip.newway.service.PlaceService;
import com.trip.newway.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.LinkedList;
import java.util.List;

import static org.springframework.util.Assert.notNull;

@Service
public class PlaceServiceImpl implements PlaceService {
    @Autowired
    private PlaceRepository placeRepository;

    @Override
    public PlaceDTO save(SavedPlaceDTO placeDTO) {
        Assert.notNull(placeDTO, "place is null");
        Place place = new Place();
        place.setName(placeDTO.getName());
        place.setLatitude(placeDTO.getLatitude());
        place.setLongitude(placeDTO.getLongitude());

        Place savedPlace = placeRepository.save(place);
        return new PlaceDTO(savedPlace.getId(), savedPlace.getName(), savedPlace.getLatitude(), savedPlace.getLongitude());
    }

    @Override
    public ResponsePlaceDTO findAll(int page) {
        if(page < 0){
            return new ResponsePlaceDTO(new LinkedList<>(), 0);
        }
        List<PlaceDTO> places = placeRepository
                .findPlaces(PageRequest.of(page, Constants.LIMIT)).getContent();
        long count = placeRepository.count();

        return new ResponsePlaceDTO(places, count);
    }

    @Override
    public PlaceDTO findByName(String name) {
        return placeRepository.findByName(name);
    }

    @Override
    public void deleteById(Long id) {
        notNull(id, "id is null");
        final Place place = placeRepository.findById(id)
                .orElseThrow(()-> new NoSuchEntityException("id isn`t found" + id));
        placeRepository.deleteById(place.getId());
    }
}

