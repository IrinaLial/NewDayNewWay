package com.trip.newway.service;

import com.trip.newway.dto.user.ResponseUserDTO;
import com.trip.newway.dto.user.SaveUsersDTO;
import com.trip.newway.dto.user.UserDTO;

public interface UserService {

    UserDTO save (SaveUsersDTO user);
    ResponseUserDTO findAll(int page);

}
