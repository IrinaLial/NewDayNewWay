package com.trip.newway.dto.role;

import com.trip.newway.model.Role;
import lombok.*;

/**
 * The {@link RoleDTO} to read a {@link Role } entity by controller.
 */

@Getter
@Setter
@AllArgsConstructor
public class RoleDTO {
    private Long id;
    private String name;
}
