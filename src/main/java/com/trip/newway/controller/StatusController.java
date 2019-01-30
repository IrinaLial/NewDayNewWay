package com.trip.newway.controller;

import com.trip.newway.dto.status.SavedStatusDTO;
import com.trip.newway.dto.status.StatusDTO;
import com.trip.newway.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusController {

    @Autowired
    private StatusService statusService;

    @PostMapping
    public ResponseEntity<StatusDTO> save(@RequestBody SavedStatusDTO status){
        StatusDTO savedStatusDTO = statusService.save(status);
        return new ResponseEntity<>(savedStatusDTO, HttpStatus.OK);
    }
}
