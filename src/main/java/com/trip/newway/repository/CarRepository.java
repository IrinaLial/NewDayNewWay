package com.trip.newway.repository;

import com.trip.newway.dto.car.CarDTO;
import com.trip.newway.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;


public interface CarRepository extends JpaRepository<Car, Long> {

    @Query("select new com.trip.newway.dto.car.CarDTO (c.id, c.name) from Car c, User u where c.userId = u.id and u.id = :userId")
    List<CarDTO> findWithUserId(@Param("userId") Long userId);
}
