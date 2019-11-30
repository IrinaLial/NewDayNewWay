package com.trip.newway.repository;

import com.trip.newway.model.UserCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserCodeRepository extends JpaRepository<UserCode,Long> {

    Optional<UserCode> findByEmail(String email);
}
