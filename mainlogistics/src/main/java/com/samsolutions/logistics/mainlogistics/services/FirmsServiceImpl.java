package com.samsolutions.logistics.mainlogistics.services;

import com.samsolutions.logistics.mainlogistics.entities.Firms;
import com.samsolutions.logistics.mainlogistics.repositories.FirmsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FirmsServiceImpl implements FirmsService {

    FirmsRepository firmsRepository;

    @Autowired
    public void setFirmsRepository(FirmsRepository firmsRepository) {
        this.firmsRepository = firmsRepository;
    }

    @Override
    public List<Firms> getAll() {

        return firmsRepository.findAll();
    }

    @Override
    public List<Firms> getAllByName(String name) {
        return null;
    }
}
