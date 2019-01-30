package com.trip.newway.service.impl;

import com.trip.newway.dto.status.SavedStatusDTO;
import com.trip.newway.dto.status.StatusDTO;
import com.trip.newway.model.Status;
import com.trip.newway.repository.StatusRepository;
import com.trip.newway.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class StatusServiceImpl implements StatusService {

    @Autowired
    private StatusRepository statusRepository;
    @Override
    public StatusDTO save(SavedStatusDTO statusDTO) {
        Assert.notNull(statusDTO,"place is null");
        Status status = new Status();
        status.setEmpty(statusDTO.getEmpty());
        status.setFreePlace(statusDTO.getFreePlace());
        status.setFull(statusDTO.getFull());

        Status savedStatus= statusRepository.save(status);
        return new StatusDTO(savedStatus.getId(),savedStatus.getEmpty(),savedStatus.getFreePlace(),savedStatus.getFull());
    }
}
