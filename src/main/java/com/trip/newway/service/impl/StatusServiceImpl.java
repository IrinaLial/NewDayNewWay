package com.trip.newway.service.impl;

import com.sun.tools.internal.ws.wsdl.framework.NoSuchEntityException;
import com.trip.newway.dto.status.SavedStatusDTO;
import com.trip.newway.dto.status.StatusDTO;
import com.trip.newway.model.Status;
import com.trip.newway.repository.StatusRepository;
import com.trip.newway.service.StatusService;
import com.trip.newway.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import static org.springframework.util.Assert.notNull;

@Service
public class StatusServiceImpl implements StatusService {

    @Autowired
    private StatusRepository statusRepository;

    @Override
    public StatusDTO save(SavedStatusDTO statusDTO) {
        Assert.notNull(statusDTO, "place is null");
        Status status = new Status();
        status.setName(statusDTO.getName());

        Status savedStatus = statusRepository.save(status);
        return new StatusDTO(savedStatus.getId(), savedStatus.getName());
    }

    @Override
    public void deleteById(Long id) {
        notNull(id, "id is null");
        final Status status = statusRepository.findById(id)
                .orElseThrow(()-> new NoSuchEntityException("id isn`t found" +id));
        statusRepository.deleteById(status.getId());
    }
}
