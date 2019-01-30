package com.trip.newway.controller;

import com.trip.newway.dto.place.PlaceDTO;
import com.trip.newway.dto.place.ResponsePlaceDTO;
import com.trip.newway.dto.place.SavedPlaceDTO;
import com.trip.newway.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PlaceController {

    @Autowired
    private PlaceService placeService;

    @PostMapping
    public ResponseEntity<PlaceDTO> save(@RequestBody SavedPlaceDTO place){
        PlaceDTO savedPlaceDTO = placeService.save(place);
        return new ResponseEntity<>(savedPlaceDTO, HttpStatus.OK);
    }
    
    @GetMapping
    public ResponseEntity<ResponsePlaceDTO> findAll(@RequestParam int page){
        ResponsePlaceDTO placeDTO = placeService.findAll(page);
        return new ResponseEntity<>(placeDTO,HttpStatus.OK);
    }
    
    @GetMapping
    public ResponseEntity<PlaceDTO>findByName(@RequestParam String name){
        final PlaceDTO placeDTO = placeService.findByName(name);
        return new ResponseEntity<>(placeDTO,HttpStatus.OK);
    }

}
