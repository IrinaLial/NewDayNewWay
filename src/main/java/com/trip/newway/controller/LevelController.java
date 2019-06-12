package com.trip.newway.controller;

import com.trip.newway.dto.level.LevelDTO;
import com.trip.newway.dto.level.SavedLevelDTO;
import com.trip.newway.service.LevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/level")
public class LevelController {

    @Autowired
    private LevelService levelService;

    @PreAuthorize(value = "hasAuthority('USER')")
    @PostMapping
    public ResponseEntity<LevelDTO> save(@RequestBody SavedLevelDTO level) {
        LevelDTO savedLevelDTO = levelService.save(level);
        return new ResponseEntity<>(savedLevelDTO, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestParam Long id) {
        levelService.deleteById(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
