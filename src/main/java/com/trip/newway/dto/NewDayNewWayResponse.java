package com.trip.newway.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class NewDayNewWayResponse {
    @JsonProperty("status")
    private ResponseDetail status;
}

