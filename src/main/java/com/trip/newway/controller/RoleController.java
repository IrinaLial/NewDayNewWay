package com.trip.newway.controller;

import com.trip.newway.dto.role.RoleDTO;
import com.trip.newway.dto.role.SavedRoleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping
    public ResponseEntity<RoleDTO> save(@RequestBody SavedRoleDTO role){
        RoleDTO savedRole = roleService.save(role);
        return new ResponseEntity<>(savedRole, HttpStatus.OK);
    }
}
