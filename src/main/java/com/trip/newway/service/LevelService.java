package com.trip.newway.service;

import com.trip.newway.dto.level.LevelDTO;
import com.trip.newway.dto.level.SavedLevelDTO;

public interface LevelService {

    LevelDTO save(SavedLevelDTO level);

    void deleteById(Long id);
}
