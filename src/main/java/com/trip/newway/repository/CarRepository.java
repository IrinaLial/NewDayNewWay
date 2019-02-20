package com.trip.newway.repository;

import com.trip.newway.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;


public interface CarRepository extends JpaRepository<Car, Long> {

    @Query("select new com.trip.newway.model.car (c.id, c.name)" + "from User u where u.id = :userId")
    List<Car> findByUserId(@Param("userId") Long userId);
}
