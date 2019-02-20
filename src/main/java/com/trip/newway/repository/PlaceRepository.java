package com.trip.newway.repository;

import com.trip.newway.dto.place.PlaceDTO;
import com.trip.newway.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PlaceRepository extends JpaRepository<Place, Long> {
    @Query("select new com.trip.newway.dto.place.PlaceDTO (p.id, p.name,p.latitude,p.longitude) from Place p where p.name = :name")
    PlaceDTO findByName(@Param("name") String name);
}
