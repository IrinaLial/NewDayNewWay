package com.trip.newway.controller;

import com.trip.newway.dto.NewDayNewWayResponse;
import com.trip.newway.dto.cars.CarDTO;
import com.trip.newway.dto.cars.ResponseCarDTO;
import com.trip.newway.dto.cars.SaveCarDTO;
import com.trip.newway.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @PreAuthorize(value = "hasAuthority('USER')")
    @PostMapping
    public ResponseEntity<CarDTO> save(@RequestBody SaveCarDTO car) {
        CarDTO savedCar = carService.save(car);
        return new ResponseEntity<>(savedCar, HttpStatus.OK);
    }

    @PreAuthorize(value = "hasAuthority('ADMIN')")
    @GetMapping("/findAll")
    public ResponseEntity<ResponseCarDTO> findAll(@RequestParam int page) {
        ResponseCarDTO responseCarDTO = carService.findAll(page);
        return new ResponseEntity<>(responseCarDTO, HttpStatus.OK);
    }

    @GetMapping("/findWithUserId")
    public ResponseEntity<List<CarDTO>> findWithUserId(@RequestParam Long userId) {
        final List<CarDTO> cars = carService.findWithUserId(userId);
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @PreAuthorize(value = "hasAuthority('USER')")
    @DeleteMapping
    public ResponseEntity<NewDayNewWayResponse> delete(@RequestParam Long id) {
        final NewDayNewWayResponse response = carService.deleteById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
