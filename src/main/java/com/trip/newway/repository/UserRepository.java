package com.trip.newway.repository;

import com.trip.newway.model.Direction;
import com.trip.newway.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
}
