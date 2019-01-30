package com.trip.newway.service.impl;

import com.trip.newway.dto.role.RoleDTO;
import com.trip.newway.dto.role.SavedRoleDTO;
import com.trip.newway.model.Role;
import com.trip.newway.model.User;
import com.trip.newway.repository.RoleRepository;
import com.trip.newway.service.RoleService;
import com.trip.newway.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;


    @Override
    public RoleDTO save(SavedRoleDTO roleDTO) {
        Assert.notNull(roleDTO,"role is null");

        Role role = new Role();
        role.setName(roleDTO.getName());

        User user = new User();
        user.setRole(roleRepository.findByName(Constants.ROLE_ADMIN));
        Role savedRole = roleRepository.save(role);

        return new RoleDTO(savedRole.getId(),savedRole.getName());
    }
}
