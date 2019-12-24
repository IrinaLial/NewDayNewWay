package com.trip.newway.util;

import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OwnRepository<MODEL,ID> {

    List<MODEL> findAll();

    MODEL findOne(ID id);

    MODEL save(MODEL model);

    @Query(value = "select new com.trip.newway.util.TestCarDTO(tc.type) from TestCar tc")
    TestCarDTO findAllDirection();


}
