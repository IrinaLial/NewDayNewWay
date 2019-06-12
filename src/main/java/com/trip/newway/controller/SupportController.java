package com.trip.newway.controller;

import com.trip.newway.dto.support.SavedSupportDTO;
import com.trip.newway.dto.support.SupportDTO;
import com.trip.newway.service.SupportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/support")
public class SupportController {

    @Autowired
    private SupportService supportService;

    @PreAuthorize(value = "hasAuthority('USER')")
    @PostMapping
    public ResponseEntity<SupportDTO> save(@RequestBody SavedSupportDTO support) {
        SupportDTO savedSupportDTO = supportService.save(support);
        return new ResponseEntity<>(savedSupportDTO, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestParam Long id) {
        supportService.deleteById(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
