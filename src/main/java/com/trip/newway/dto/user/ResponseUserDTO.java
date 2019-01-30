package com.trip.newway.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ResponseUserDTO {
    private List<UserDTO> user;
    private long total;
}
