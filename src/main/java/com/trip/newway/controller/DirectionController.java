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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/direction")
public class DirectionController {

    @Autowired
    private DirectionService directionService;

    @PreAuthorize(value = "hasAuthority('ADMIN')")
    @GetMapping("/findAll")
    public ResponseEntity<ResponseDirectionDTO> findAll(@RequestParam int page) {
        ResponseDirectionDTO responseDirectionDTO = directionService.findAll(page);
        return new ResponseEntity<>(responseDirectionDTO, HttpStatus.OK);
    }
    @PreAuthorize(value = "hasAuthority('USER')")
    @PostMapping
    public ResponseEntity<DirectionDTO> save(@RequestBody SavedDirectionDTO direction) {
        DirectionDTO savedDirectionDTO = directionService.save(direction);
        return new ResponseEntity<>(savedDirectionDTO, HttpStatus.OK);
    }

    @GetMapping("/findByName")
    public ResponseEntity<DirectionDTO> findByName(@RequestParam String name) {
        final DirectionDTO directionDTO = directionService.findByName(name);
        return new ResponseEntity<>(directionDTO, HttpStatus.OK);
    }

    @GetMapping("/findWithUserId")
    public ResponseEntity<List<DirectionDTO>> findWithUserId(@RequestParam Long userId, @RequestParam int page) {
        final List<DirectionDTO> directions = directionService.findWithUserId(userId, page);
        return new ResponseEntity<>(directions, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestParam Long id) {
        directionService.deleteById(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
