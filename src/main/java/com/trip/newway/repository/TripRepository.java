package com.trip.newway.repository;

import com.trip.newway.dto.trips.FullTripDTO;
import com.trip.newway.dto.trips.TripDTO;
import com.trip.newway.model.Trip;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TripRepository extends JpaRepository<Trip, Long> {

    @Query("select new com.trip.newway.dto.trips.FullTripDTO(t.id, t.countPlaces, t.estimateTime, t.placeFromCoordinate, t.placeToCoordinate, t.placeFrom, t.placeTo, t.price, t.message, t.date) from Trip t where t.userId = :id")
    FullTripDTO findWithId(@Param("id") Long id);

    @Query(value = "select new com.trip.newway.dto.trips.TripDTO(t.id, t.placeFrom, t.placeTo, t.price, t.date) from Trip t where t.userId = :userId")
    Page<TripDTO> findWithUserId(@Param("userId") Long userId, Pageable pageable);

    @Query(value = "select new com.trip.newway.dto.trips.TripDTO(t.id, t.placeFrom, t.placeTo, t.price, t.date) from Trip t where t.status = 1")
    Page<TripDTO> findActiveTrips(Pageable pageable);
}
