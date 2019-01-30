package com.trip.newway.dto.user;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private boolean isActive;



}
