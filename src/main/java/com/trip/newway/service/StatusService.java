package com.trip.newway.service;

import com.trip.newway.dto.status.SavedStatusDTO;
import com.trip.newway.dto.status.StatusDTO;

public interface StatusService {

    StatusDTO save(SavedStatusDTO status);

    void deleteById(Long id);
}
