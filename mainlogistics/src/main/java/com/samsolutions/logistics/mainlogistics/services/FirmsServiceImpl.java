package com.samsolutions.logistics.mainlogistics.services;

import com.samsolutions.logistics.mainlogistics.dto.FirmDTO;
import com.samsolutions.logistics.mainlogistics.entities.Firms;
import com.samsolutions.logistics.mainlogistics.entities.Users;
import com.samsolutions.logistics.mainlogistics.repositories.FirmsRepository;
import com.samsolutions.logistics.mainlogistics.repositories.UsersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FirmsServiceImpl implements FirmsService {

    private FirmsRepository firmsRepository;
    private UsersRepository usersRepository;

    @Autowired
    public void setFirmsRepository(FirmsRepository firmsRepository) {
        this.firmsRepository = firmsRepository;
    }
    @Autowired
    public void setUsersRepository(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }



    @Override
    public List<FirmDTO> getAll() {

        return null;
    }

    @Override
    public List<FirmDTO> getAllByName(String firmName) {

       List<Firms> firmsList = firmsRepository.findDistinctTop5ByFirmNameLike(firmName+"%");
       List<FirmDTO> firmDTOList = new ArrayList<>(firmsList.size());
       ModelMapper modelMapper = new ModelMapper();

       for(int i=0;i<firmsList.size();i++){

           firmDTOList.add(new FirmDTO());
           modelMapper.map(firmsList.get(i),firmDTOList.get(i));

       }
       return firmDTOList;

    }

    @Override
    public FirmDTO getByEmail(String email) {
        FirmDTO firmDTO=new FirmDTO();
        Firms firm = firmsRepository.findAllByEmail(email).get(0);
        map(firm,firmDTO);
        return firmDTO;
    }

    @Override
    public void update(String email,FirmDTO firmDTO) {
        Firms firms = firmsRepository.findAllByEmail(email).get(0);
        Users users = usersRepository.findByEmail(email);
        users.setEmail(firmDTO.getEmail());
        map(firmDTO,firms);
        usersRepository.save(users);
        firmsRepository.save(firms);
    }

    @Override
    public void map(Object src, Object dest) {
        ModelMapper modelMapper=new ModelMapper();
        modelMapper.map(src,dest);
    }
}
