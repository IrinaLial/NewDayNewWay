package com.trip.newway.service.impl;

import com.sun.tools.internal.ws.wsdl.framework.NoSuchEntityException;
import com.trip.newway.dto.support.SavedSupportDTO;
import com.trip.newway.dto.support.SupportDTO;
import com.trip.newway.model.Support;
import com.trip.newway.repository.SupportRepository;
import com.trip.newway.service.SupportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import static org.springframework.util.Assert.notNull;

@Service
public class SupportServiceImpl implements SupportService {

    @Autowired
    private SupportRepository supportRepository;

    @Override
    public SupportDTO save(SavedSupportDTO supportDTO) {
        Assert.notNull(supportDTO, "Support is null");
        Support support = new Support();
        support.setSubject(supportDTO.getSubject());
        support.setText(supportDTO.getText());

        Support savedSupport = supportRepository.save(support);
        return new SupportDTO(savedSupport.getId(), savedSupport.getSubject(), savedSupport.getText());
    }

    @Override
    public void deleteById(Long id) {
        notNull(id, "id is null");
        final Support support = supportRepository.findById(id)
                .orElseThrow(()-> new NoSuchEntityException("id isn`t found" + id));
        supportRepository.deleteById(support.getId());
    }
}
