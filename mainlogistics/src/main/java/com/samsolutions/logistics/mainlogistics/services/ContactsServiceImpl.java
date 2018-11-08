package com.samsolutions.logistics.mainlogistics.services;

import com.samsolutions.logistics.mainlogistics.dto.ContactDTO;
import com.samsolutions.logistics.mainlogistics.entities.Contacts;
import com.samsolutions.logistics.mainlogistics.entities.Firms;
import com.samsolutions.logistics.mainlogistics.entities.Passwords;
import com.samsolutions.logistics.mainlogistics.repositories.ContactsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactsServiceImpl<T,V> implements ContactsService<T,V> {

    private T tEntity;
    private V vDTO;

    private ContactsRepository contactsRepository;

    @Autowired
    public void setContactsRepository(ContactsRepository contactsRepository) {
        this.contactsRepository = contactsRepository;
    }

    public void setProperties(T tEntity, V vDTO){

        this.tEntity=tEntity;
        this.vDTO=vDTO;

        ModelMapper modelMapper=new ModelMapper();
        modelMapper.map(vDTO,tEntity);

    }

    public T gettEntity() {
        return tEntity;
    }

    public V getvDTO() {
        return vDTO;
    }

    public void settEntity(T tEntity) {
        this.tEntity = tEntity;
    }

    public void setvDTO(V vDTO) {
        this.vDTO = vDTO;
    }

    @Override
    public void save( Passwords passwords, Firms firms) {


        tEntity.setPasswordsId(passwords.getId());
        contactsRepository.save(contacts);

    }


}
