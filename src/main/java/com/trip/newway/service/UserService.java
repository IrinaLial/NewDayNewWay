package com.trip.newway.service;

import com.trip.newway.dto.NewDayNewWayResponse;
import com.trip.newway.dto.user.ResponseUserDTO;
import com.trip.newway.dto.user.SaveUsersDTO;
import com.trip.newway.dto.user.UserDTO;

public interface UserService {

    boolean sendCode(String email);

    UserDTO save(SaveUsersDTO user);

    boolean emailConfirmation(String email);

    boolean emailCodeConfirm(String email,int code);

    boolean passwordConfirm(String email,int code, String password);

    ResponseUserDTO findAll(int page);

    NewDayNewWayResponse deleteById(Long id);


}
