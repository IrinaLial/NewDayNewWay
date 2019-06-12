package com.trip.newway.service.impl;

import com.sun.tools.internal.ws.wsdl.framework.NoSuchEntityException;
import com.trip.newway.dto.user.ResponseUserDTO;
import com.trip.newway.dto.user.SaveUsersDTO;
import com.trip.newway.dto.user.UserDTO;
import com.trip.newway.exception.EmailNotUniqueException;
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

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;

import static org.springframework.util.Assert.notNull;

@Transactional
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


    @Override
    public UserDTO save(SaveUsersDTO userDTO) {
        Assert.notNull(userDTO, "user is null");

        val presentByEmail = userRepository.findByEmail(userDTO.getEmail());
        if (presentByEmail != null) {
            throw new EmailNotUniqueException("email is already exist");
        }

        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        val passwordEncoder = new BCryptPasswordEncoder();
        String encodePassword = passwordEncoder.encode(userDTO.getPassword());
        user.setPassword(encodePassword);
        user.setActive(true);

        Role role = roleRepository.findByName(Constants.ROLE_USER);
        user.setRole(role);
        user.setLevelId(1L);
        user.setActive(false);
        user.setStatusId(Constants.EMPTY);

        User savedUser = userRepository.save(user);
        return new UserDTO(savedUser.getId(), savedUser.getName(),
                savedUser.getEmail(), savedUser.isActive());
    }

    @Override
    public ResponseUserDTO findAll(int page) {
        if (page < 0) {
            return new ResponseUserDTO(new LinkedList<>(), 0);
        }
        List<UserDTO> users = userRepository
                .findUsers(PageRequest.of(page, Constants.LIMIT)).getContent();
        long count = userRepository.count();

        return new ResponseUserDTO(users, count);
    }

    @Override
    public void deleteById(Long id) {
        notNull(id, "id is null");
        final User user = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchEntityException("User no found with id: " + id));
        userRepository.deleteById(user.getId());
    }
}
