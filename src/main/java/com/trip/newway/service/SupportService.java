package com.trip.newway.service;

import com.trip.newway.dto.support.SavedSupportDTO;
import com.trip.newway.dto.support.SupportDTO;

public interface SupportService {

    SupportDTO save(SavedSupportDTO support);

    void deleteById(Long id);

}
