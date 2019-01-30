package com.trip.newway.controller;

import com.trip.newway.dto.car.CarDTO;
import com.trip.newway.dto.car.ResponseCarDTO;
import com.trip.newway.dto.car.SavedCarDTO;
import com.trip.newway.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/car")
public class CarController {

    @Autowired
    private CarService carService;

    @PostMapping
    public ResponseEntity<CarDTO> save(@RequestBody SavedCarDTO car){
        CarDTO savedCar = carService.save(car);
        return new ResponseEntity<>(savedCar, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<ResponseCarDTO> findAll(@RequestParam int page){
        ResponseCarDTO responseCarDTO = carService.findAll(page);
        return new ResponseEntity<>(responseCarDTO,HttpStatus.OK);
    }
}
