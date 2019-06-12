package com.trip.newway.auth.service.impl;

import com.sun.tools.internal.ws.wsdl.framework.NoSuchEntityException;
import com.trip.newway.auth.JwtUserFactory;
import com.trip.newway.auth.service.TokenHandler;
import com.trip.newway.model.User;
import com.trip.newway.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Date;

/**
 * {@inheritDoc}.
 */
@Component("tokenHandler")
@PropertySource("classpath:security.properties")
@RequiredArgsConstructor
public class TokenHandlerImpl implements TokenHandler {

    @Value("${app.jwt.secret}")
    private String secret;
    private final UserRepository userRepository;

    /**
     * {@inheritDoc}.
     */
    @Override
    public UserDetails parseUserFromToken(String token) {
        String userId = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .get("id")
                .toString();
        long id = Long.parseLong(userId);
        final User user = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchEntityException("Failed to retrieve user with id: " + id));
        return JwtUserFactory.create(user);
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public Long getUserIdFromToken(String token) {
        String userId = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .get("id")
                .toString();
        return Long.parseLong(userId);
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public User getUserFromToken(String token) {
        String userId = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .get("id")
                .toString();
        final long id = Long.parseLong(userId);
        return userRepository.findById(id)
                .orElseThrow(() -> new NoSuchEntityException("Failed to retrieve user with id: " + id));
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public String createTokenForUser(User user) {
        ZonedDateTime afterOneWeek = ZonedDateTime.now().plusWeeks(1);

        return Jwts.builder()
                .claim("id", user.getId())
                .signWith(SignatureAlgorithm.HS256, secret)
                .setExpiration(Date.from(afterOneWeek.toInstant()))
                .compact();
    }
}
