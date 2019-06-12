package com.trip.newway.service.impl;

import com.sun.tools.internal.ws.wsdl.framework.NoSuchEntityException;
import com.trip.newway.dto.level.LevelDTO;
import com.trip.newway.dto.level.SavedLevelDTO;
import com.trip.newway.model.Level;
import com.trip.newway.repository.LevelRepository;
import com.trip.newway.service.LevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import static org.springframework.util.Assert.notNull;

@Service
public class LevelServiceImpl implements LevelService {
    @Autowired
    private LevelRepository levelRepository;

    @Override
    public LevelDTO save(SavedLevelDTO levelDTO) {
        Assert.notNull(levelDTO, "level is null");

        Level level = new Level();
        level.setName(levelDTO.getName());
        Level savedLevel = levelRepository.save(level);
        return new LevelDTO(savedLevel.getId(), savedLevel.getName());
    }

    @Override
    public void deleteById(Long id) {
        notNull(id, "id is null");
        final Level level = levelRepository.findById(id)
                .orElseThrow(()-> new NoSuchEntityException("id is not found" + id));
        levelRepository.deleteById(level.getId());
    }
}