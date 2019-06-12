package com.trip.newway.auth.controller;

import com.trip.newway.auth.service.SecurityContextService;
import com.trip.newway.auth.service.TokenHandler;
import com.trip.newway.model.User;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST authentication Controller.
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final TokenHandler tokenHandler;
    private final SecurityContextService securityContextService;

    /**
     * Perform {@link User} system authentication.
     *
     * @param params {@link AuthParams} entity login and password.
     * @return {@link AuthResponse} that encapsulates the authentication token.
     * @throws AuthenticationException if Authentication failed.
     */
    @PostMapping("/auth")
    public AuthResponse login(@RequestBody AuthParams params) throws AuthenticationException {
        UsernamePasswordAuthenticationToken loginToken = params.toAuthenticationToken();
        Authentication authentication = authenticationManager.authenticate(loginToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = securityContextService.currentUser();
        String token = tokenHandler.createTokenForUser(user);
        return new AuthResponse(token);
    }

    /**
     * Encapsulates entity login and password.
     */
    @Value
    private static final class AuthParams {
        private final String email;
        private final String password;

        UsernamePasswordAuthenticationToken toAuthenticationToken() {
            return new UsernamePasswordAuthenticationToken(email, password);
        }
    }

    /**
     * Encapsulates the entity authentication token.
     */
    @Value
    @SuppressWarnings("PMD.UnusedPrivateField")
    private static final class AuthResponse {
        private final String token;
    }
}