package com.trip.newway.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveUsersDTO {
    private String name;
    private String email;
    private String password;
    private int code;
}
