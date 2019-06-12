package com.trip.newway.auth;

import com.trip.newway.model.User;
import com.trip.newway.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * Represents service for entity-specific data loading.
 */
@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(login);
        AccountStatusUserDetailsChecker detailsChecker = new AccountStatusUserDetailsChecker();
        if (user == null) {
            throw new UsernameNotFoundException("User with login: " + login + " is not found.");
        }
        JwtUser jwtUser = JwtUserFactory.create(user);
        detailsChecker.check(jwtUser);
        return jwtUser;
    }
}
