package com.trip.newway.repository;

import com.trip.newway.dto.direction.DirectionDTO;
import com.trip.newway.model.Direction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DirectionRepository extends JpaRepository<Direction, Long> {

  @Query("select new com.trip.newway.dto.direction.DirectionDTO(d.id, d.name ) from Direction d where d.name = :name")
  DirectionDTO findWithName(@Param("name") String name);

  @Query("select new com.trip.newway.dto.direction.DirectionDTO (d.id, d.name) from Direction d, User u where d.userId = u.id and u.id = :userId")
  List<DirectionDTO> findWithUserId(@Param("userId") Long userId, Pageable pageable);

  @Query(value = "select new com.trip.newway.dto.direction.DirectionDTO (d.id, d. name) from Direction d")
  Page<DirectionDTO> findDirections(Pageable pageable);

}
