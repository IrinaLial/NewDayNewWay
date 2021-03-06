package com.trip.newway.controller;

import com.trip.newway.dto.status.SavedStatusDTO;
import com.trip.newway.dto.status.StatusDTO;
import com.trip.newway.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/status")
public class StatusController {

    @Autowired
    private StatusService statusService;

    @PreAuthorize(value = "hasAuthority('USER')")
    @PostMapping
    public ResponseEntity<StatusDTO> save(@RequestBody SavedStatusDTO status) {
        StatusDTO savedStatusDTO = statusService.save(status);
        return new ResponseEntity<>(savedStatusDTO, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestParam Long id) {
        statusService.deleteById(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
