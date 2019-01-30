package com.trip.newway.service.impl;

import com.trip.newway.dto.level.LevelDTO;
import com.trip.newway.dto.level.SavedLevelDTO;
import com.trip.newway.model.Level;
import com.trip.newway.repository.LevelRepository;
import com.trip.newway.service.LevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class LevelServiceImpl implements LevelService {
    @Autowired
    private LevelRepository levelRepository;

    @Override
    public LevelDTO save(SavedLevelDTO levelDTO) {
        Assert.notNull(levelDTO,"level is null");

        Level level = new Level();
        level.setName(levelDTO.getName());
        Level savedLevel = levelRepository.save(level);
        return new LevelDTO(savedLevel.getId(),savedLevel.getName());
    }
}