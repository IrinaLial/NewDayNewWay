package com.trip.newway.repository;

import com.trip.newway.dto.direction.DirectionDTO;
import com.trip.newway.model.Direction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface DirectionRepository extends JpaRepository<Direction, Long> {

  @Query("select new com.trip.newway.dto.direction.DirectionDTO(d.id, p.name, p.name, d.date, d.freePlaces, d.estimateTime) from Direction d, Place p where d.id = :id and d.placeIdFrom = p.id ")
  DirectionDTO findWithId(@Param("id") Long id);

  @Query("select new com.trip.newway.dto.direction.DirectionDTO (d.id, p.name, p.name, d.date, d.freePlaces, d.estimateTime) from Direction d, User u, Place p where d.userId = u.id  and p.id = d.placeIdFrom and u.id = :userId")
  List<DirectionDTO> findWithUserId(@Param("userId") Long userId, Pageable pageable);

  @Query(value = "select new com.trip.newway.dto.direction.DirectionDTO (d.id, p.name, p.name, d.date, d.freePlaces, d.estimateTime) from Direction d, Place p where d.placeIdFrom = p.id")
  Page<DirectionDTO> findDirections(Pageable pageable);

  @Query("select new com.trip.newway.dto.direction.DirectionDTO(d.id, p.name, p.name, d.date, d.freePlaces, d.estimateTime) from Direction d, Place p where d.placeIdFrom = :placeFrom and d.placeIdTo = :placeTo and d.date=:date ")
 Page<DirectionDTO> findWithSearch(@Param("placeFrom") Long placeFrom, @Param("placeTo") Long placeTo, @Param("date") LocalDateTime date);

}
