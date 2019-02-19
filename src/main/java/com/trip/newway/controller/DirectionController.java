package com.trip.newway.controller;

import com.trip.newway.dto.direction.DirectionDTO;
import com.trip.newway.dto.direction.ResponseDirectionDTO;
import com.trip.newway.dto.direction.SavedDirectionDTO;
import com.trip.newway.model.Direction;
import com.trip.newway.service.DirectionService;
import com.trip.newway.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/direction")
public class DirectionController {

    @Autowired
    private DirectionService directionService;

    @GetMapping
    public ResponseEntity<ResponseDirectionDTO> findAll(@RequestParam int page){
        ResponseDirectionDTO responseDirectionDTO = directionService.findAll(page);
        return new ResponseEntity<>(responseDirectionDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity <DirectionDTO> save(@RequestBody SavedDirectionDTO direction){
        DirectionDTO savedDirectionDTO = directionService.save(direction);
        return new ResponseEntity<>(savedDirectionDTO,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<DirectionDTO> findByName(@RequestParam String name){
        final DirectionDTO directionDTO = directionService.findByName(name);
        return new ResponseEntity<>(directionDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Direction>> findByUserId(@RequestParam Long userId, @RequestParam int page){
        final List<Direction> directions =  directionService.findByUserId(userId,page);
        return new ResponseEntity<>(directions,HttpStatus.OK);
    }
}
