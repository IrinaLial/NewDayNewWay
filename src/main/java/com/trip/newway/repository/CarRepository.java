package com.trip.newway.repository;

import com.trip.newway.dto.cars.CarDTO;
import com.trip.newway.model.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;


public interface CarRepository extends JpaRepository<Car, Long> {

    @Query("select new com.trip.newway.dto.cars.CarDTO(c.id, c.name, c.countPlaces) from Car c, User u where c.userId = u.id and u.id = :userId")
    List<CarDTO> findWithUserId(@Param("userId") Long userId);


    @Query(value = "select new com.trip.newway.dto.cars.CarDTO(c.id, c.name, c.countPlaces) from Car c")
    Page<CarDTO> findCars(Pageable pageable);
}
