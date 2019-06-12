package com.trip.newway.auth.service.impl;

import com.trip.newway.auth.service.SecurityContextService;
import com.trip.newway.model.User;
import com.trip.newway.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * {@inheritDoc}.
 */
@Service("securityContextService")
@RequiredArgsConstructor
public class SecurityContextServiceImpl implements SecurityContextService {
    private final UserRepository userRepository;

    /**
     * {@inheritDoc}.
     */
    @Override
    public User currentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByEmail(authentication.getName());
    }
}
