package com.trip.newway.dto.support;

import com.trip.newway.model.Support;
import com.trip.newway.model.User;
import lombok.*;

/**
 * The {@link SupportDTO} to read a {@link Support } entity by controller.
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SupportDTO {
    private Long id;
    private String subject;
    private String text;

}
