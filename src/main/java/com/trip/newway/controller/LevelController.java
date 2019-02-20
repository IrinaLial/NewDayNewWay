package com.trip.newway.controller;

import com.trip.newway.dto.level.LevelDTO;
import com.trip.newway.dto.level.SavedLevelDTO;
import com.trip.newway.service.LevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LevelController {
    @Autowired
    private LevelService levelService;

    @PostMapping
    public ResponseEntity<LevelDTO> save(@RequestBody SavedLevelDTO level) {
        LevelDTO savedLevelDTO = levelService.save(level);
        return new ResponseEntity<>(savedLevelDTO, HttpStatus.OK);
    }

}
