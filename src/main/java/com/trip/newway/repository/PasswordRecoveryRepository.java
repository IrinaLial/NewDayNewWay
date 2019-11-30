package com.trip.newway.repository;

import com.trip.newway.model.PasswordRecovery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PasswordRecoveryRepository extends JpaRepository<PasswordRecovery,Long> {

    Optional<PasswordRecovery> findByEmail(String email);
}
