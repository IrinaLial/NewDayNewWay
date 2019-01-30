package com.trip.newway.service.impl;

import com.trip.newway.dto.direction.DirectionDTO;
import com.trip.newway.dto.direction.ResponseDirectionDTO;
import com.trip.newway.dto.direction.SavedDirectionDTO;
import com.trip.newway.model.Direction;
import com.trip.newway.repository.DirectionRepository;
import com.trip.newway.service.DirectionService;
import com.trip.newway.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.LinkedList;
import java.util.List;

@Service
public class DirectionServiceImpl implements DirectionService {

    @Autowired
    private DirectionRepository directionRepository;


    @Override
    public DirectionDTO save(SavedDirectionDTO directionDTO) {
        Assert.notNull(directionDTO,"direction is null");

        Direction direction = new Direction();
        direction.setName(directionDTO.getName());
        direction.setStatus(directionDTO.getStatus());

        Direction savedDirection = directionRepository.save(direction);
        return new DirectionDTO(savedDirection.getId(),savedDirection.getName(),savedDirection.getStatus());
    }

    @Override
    public ResponseDirectionDTO findAll(int page) {
        List<Direction> directions = directionRepository
                .findAll(PageRequest.of(page, Constants.LIMIT)).getContent();
        List<DirectionDTO> directionDTOS = new LinkedList<>();
        directions.forEach(s->{
            DirectionDTO directionDTO = new DirectionDTO(s.getId(),s.getName(),s.getStatus());
            directionDTOS.add(directionDTO);
        });
        long count = directionRepository.count();

        return new ResponseDirectionDTO(directionDTOS,count);
    }

    @Override
    public DirectionDTO findByName(String name) {

        return directionRepository.findByName(name);
    }
    //todo ask about "findByName"
}
