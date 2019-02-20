package com.trip.newway.service.impl;

import com.trip.newway.dto.user.ResponseUserDTO;
import com.trip.newway.dto.user.SaveUsersDTO;
import com.trip.newway.dto.user.UserDTO;
import com.trip.newway.model.Role;
import com.trip.newway.model.User;
import com.trip.newway.repository.RoleRepository;
import com.trip.newway.repository.UserRepository;
import com.trip.newway.service.UserService;
import com.trip.newway.util.Constants;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.LinkedList;
import java.util.List;


@Service
public class UsersServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDTO save(SaveUsersDTO userDTO) {
        Assert.notNull(userDTO, "user is null");
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        val passwordEncoder = new BCryptPasswordEncoder();
        String encodePassword = passwordEncoder.encode(userDTO.getPassword());
        user.setPassword(encodePassword);
        user.setActive(true);

        Role role = roleRepository.findByName(Constants.ROLE_USER);
        user.setRole(role);

        User savedUser = userRepository.save(user);
        return new UserDTO(savedUser.getId(), savedUser.getName(),
                savedUser.getEmail(), savedUser.isActive());
    }

    @Override
    public ResponseUserDTO findAll(int page) {
        List<User> user = userRepository
                .findAll(PageRequest.of(page, Constants.LIMIT)).getContent();
        List<UserDTO> userDTOS = new LinkedList<>();
        user.forEach(s -> {
            UserDTO dto = new UserDTO(s.id, s.getName(), s.getEmail(), s.isActive());
            userDTOS.add(dto);
        });

        long count = userRepository.count();

        return new ResponseUserDTO(userDTOS, count);
    }

}
