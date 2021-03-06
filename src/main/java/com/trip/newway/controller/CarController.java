package com.trip.newway.controller;

import com.trip.newway.dto.car.CarDTO;
import com.trip.newway.dto.car.ResponseCarDTO;
import com.trip.newway.dto.car.SavedCarDTO;
import com.trip.newway.model.Car;
import com.trip.newway.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/car")
public class CarController {

    @Autowired
    private CarService carService;

    @PreAuthorize(value = "hasAuthority('USER')")
    @PostMapping
    public ResponseEntity<CarDTO> save(@RequestBody SavedCarDTO car) {
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
    public ResponseEntity delete(@RequestParam Long id) {
        carService.deleteById(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
