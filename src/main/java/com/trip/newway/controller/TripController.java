package com.trip.newway.controller;

import com.trip.newway.dto.NewDayNewWayResponse;
import com.trip.newway.dto.trips.FullTripDTO;
import com.trip.newway.dto.trips.ResponseTripDTO;
import com.trip.newway.dto.trips.SaveTripDTO;
import com.trip.newway.dto.trips.TripDTO;
import com.trip.newway.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trips")
public class TripController {

    @Autowired
    private TripService tripService;

    @PreAuthorize(value = "hasAuthority('USER')")
    @PostMapping
    public ResponseEntity<NewDayNewWayResponse> save(@RequestBody SaveTripDTO direction) {
        final NewDayNewWayResponse response = tripService.save(direction);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/applyUser")
    public ResponseEntity<Boolean> applyUser(@RequestParam Long userId, @RequestParam Long id) {
        boolean applyUser = tripService.applyUser(userId, id);
        return new ResponseEntity<>(applyUser, HttpStatus.OK);
    }

    @PostMapping("/cancel")
    public ResponseEntity<Boolean> cancelTrip(@RequestParam Long id) {
        boolean cancelTrip = tripService.cancelTrip(id);
        return new ResponseEntity<>(cancelTrip,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<FullTripDTO> findWithId(@RequestParam Long id) {
        final FullTripDTO fullTripDTO = tripService.findWithId(id);
        return new ResponseEntity<>(fullTripDTO, HttpStatus.OK);
    }

    @GetMapping("/userId")
    public ResponseEntity<List<TripDTO>> findWithUserId(@RequestParam Long userId, @RequestParam int page) {
        final List<TripDTO> directions = tripService.findWithUserId(userId, page);
        return new ResponseEntity<>(directions, HttpStatus.OK);
    }

    @PreAuthorize(value = "hasAuthority('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<ResponseTripDTO> findAll(@RequestParam int page) {
        ResponseTripDTO responseTripDTO = tripService.findAll(page);
        return new ResponseEntity<>(responseTripDTO, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<NewDayNewWayResponse> delete(@RequestParam Long id) {
        final NewDayNewWayResponse response = tripService.deleteById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
