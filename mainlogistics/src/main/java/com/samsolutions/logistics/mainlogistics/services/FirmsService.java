package com.samsolutions.logistics.mainlogistics.services;

import com.samsolutions.logistics.mainlogistics.dto.FirmDTO;
import com.samsolutions.logistics.mainlogistics.entities.Firms;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface FirmsService {
    List<FirmDTO> getAll();
    List<FirmDTO> getAllByName(String firmName);
}
