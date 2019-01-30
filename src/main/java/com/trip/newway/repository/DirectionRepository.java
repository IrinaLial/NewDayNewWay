package com.trip.newway.repository;

import com.trip.newway.dto.direction.DirectionDTO;
import com.trip.newway.model.Direction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



public interface DirectionRepository extends JpaRepository<Direction,Long> {
  @Query("select new com.trip.newway.dto.direction.DirectionDTO (d.id, d.name) from Direction d where d.name = :name")
    DirectionDTO findByName(@Param("name") String name);

}
