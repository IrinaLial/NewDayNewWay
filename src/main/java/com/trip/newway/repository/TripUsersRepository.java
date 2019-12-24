package com.trip.newway.repository;

import com.trip.newway.dto.email.EmailTripDTO;
import com.trip.newway.model.TripUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TripUsersRepository extends JpaRepository<TripUsers, Long> {

    @Query("select new com.trip.newway.dto.email.EmailTripDTO (u.name, concat(t.placeFrom, ' ', t.placeTo) , u.email) from  TripUsers tu, User u, Trip t where tu.tripId = :id and tu.userId = u.id and tu.tripId = t.id")
    List<EmailTripDTO> findUsersWithTripId(@Param("id") Long id);
}
