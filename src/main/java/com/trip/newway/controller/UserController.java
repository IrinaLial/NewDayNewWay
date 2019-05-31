package com.trip.newway.controller;

import com.trip.newway.dto.user.ResponseUserDTO;
import com.trip.newway.dto.user.SaveUsersDTO;
import com.trip.newway.dto.user.UserDTO;
import com.trip.newway.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserDTO> save(@RequestBody SaveUsersDTO user) {
        UserDTO savedUser = userService.save(user);
        return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }

    @GetMapping("/findAll")
    public ResponseEntity<ResponseUserDTO> findAll(@RequestParam int page) {
        ResponseUserDTO userDTO = userService.findAll(page);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestParam Long id) {
        userService.deleteById(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
