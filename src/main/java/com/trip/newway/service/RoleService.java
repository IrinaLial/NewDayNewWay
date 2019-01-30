package com.trip.newway.service;

import com.trip.newway.dto.role.RoleDTO;
import com.trip.newway.dto.role.SavedRoleDTO;

public interface RoleService {

    RoleDTO save(SavedRoleDTO role);

}
