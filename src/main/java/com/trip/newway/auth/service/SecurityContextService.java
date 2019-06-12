package com.trip.newway.auth.service;

import com.trip.newway.model.User;

/**
 * The <code>SecurityContextService</code> provides means for obtaining
 * currently authenticated {@link User} entity.
 */
public interface SecurityContextService {

    /**
     * Returns {@link User} entity, who's {@link org.springframework.security.core.Authentication}
     * token is present in {@link org.springframework.security.core.context.SecurityContextHolder}.
     *
     * @return {@link User} entity.
     */
    User currentUser();
}
