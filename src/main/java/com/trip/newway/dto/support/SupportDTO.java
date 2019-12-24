package com.trip.newway.dto.support;

import com.trip.newway.model.Support;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The {@link SupportDTO} to read a {@link Support } entity by controller.
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SupportDTO {
    private Long id;
    private String text;
}
