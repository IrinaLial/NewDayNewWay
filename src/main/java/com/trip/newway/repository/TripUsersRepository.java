package com.trip.newway.repository;

import com.trip.newway.dto.email.EmailTripDTO;
import com.trip.newway.model.TripUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TripUsersRepository extends JpaRepository<TripUsers, Long> {
    @Query("select new com.trip.newway.dto.email.EmailTripDTO (u.name, d.name, u.email) from  TripUsers tu, User u, Direction d where tu.directionId = :id and tu.userId = u.id and tu.directionId = d.id")
    List<EmailTripDTO> findUsersWithTripId(@Param("id") Long id);

}
