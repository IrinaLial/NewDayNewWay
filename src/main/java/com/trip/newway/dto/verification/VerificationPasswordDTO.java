package com.trip.newway.dto.verification;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class VerificationPasswordDTO {
    private String email;
    private int code;
}
