package com.trip.newway.dto.verification;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class VerificationCodeDTO {
    private String email;
    private int code;
}
