package com.samsolutions.logistics.mainlogistics.services.user.impl;

import com.samsolutions.logistics.mainlogistics.dto.ContactDTO;
import com.samsolutions.logistics.mainlogistics.dto.FirmDTO;
import com.samsolutions.logistics.mainlogistics.dto.PageDTO;
import com.samsolutions.logistics.mainlogistics.entities.Contacts;
import com.samsolutions.logistics.mainlogistics.entities.Firms;
import com.samsolutions.logistics.mainlogistics.entities.Users;
import com.samsolutions.logistics.mainlogistics.repositories.ContactsRepository;
import com.samsolutions.logistics.mainlogistics.repositories.FirmsRepository;
import com.samsolutions.logistics.mainlogistics.repositories.UsersRepository;
import com.samsolutions.logistics.mainlogistics.services.security.ContactState;
import com.samsolutions.logistics.mainlogistics.services.security.Role;
import com.samsolutions.logistics.mainlogistics.services.user.FirmsService;
import com.samsolutions.logistics.mainlogistics.validation.exceptions.FirmNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.*;

/**
 * Firms service
 */
@Service
public class FirmsServiceImpl implements FirmsService {

    private FirmsRepository firmsRepository;
    private UsersRepository usersRepository;
    private ContactsRepository contactsRepository;

    private enum OrderTypes { ORDER_BY_FIRST_NAME,ORDER_BY_LAST_NAME,ORDER_BY_MODIFIRIED_DATE };

    @Autowired
    public void setFirmsRepository(FirmsRepository firmsRepository) {
        this.firmsRepository = firmsRepository;
    }

    @Autowired
    public void setUsersRepository(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Autowired
    public void setContactsRepository(ContactsRepository contactsRepository) {
        this.contactsRepository = contactsRepository;
    }


    @Override
    public List<FirmDTO> getAll() {

        return null;
    }

    @Override
    public List<FirmDTO> getAllByName(String firmName) {
        List<Firms> firmsList = firmsRepository.findDistinctTop5ByFirmNameLike(firmName + "%");
        List<FirmDTO> firmDTOList = new ArrayList<>(firmsList.size());
        ModelMapper modelMapper = new ModelMapper();

        for (int i = 0; i < firmsList.size(); i++) {

            firmDTOList.add(new FirmDTO());
            modelMapper.map(firmsList.get(i), firmDTOList.get(i));

        }
        return firmDTOList;

    }

    @Override
    public FirmDTO getByEmail(String email) {
        FirmDTO firmDTO = new FirmDTO();
        Firms firm = firmsRepository.findAllByEmail(email).get(0);
        map(firm, firmDTO);
        return firmDTO;
    }

    @Override
    public void update(String email, FirmDTO firmDTO) {
        Firms firms = firmsRepository.findAllByEmail(email).get(0);
        Users users = usersRepository.findByEmail(email);
        users.setEmail(firmDTO.getEmail());
        map(firmDTO, firms);
        usersRepository.save(users);
        firmsRepository.save(firms);
    }

    @Override
    public void addContact(ContactDTO contactDTO) {
        Contacts contacts = contactsRepository.findByEmail(contactDTO.getEmail());
        Firms firms = firmsRepository.findAllByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).get(0);
        contacts.setContactState(ContactState.ADDED);
        Users users = usersRepository.findByEmail(contactDTO.getEmail());
        users.setRole(Role.ROLE_CONTACT_LOGISTICS_FIRM_USER);
        contactsRepository.save(contacts);
    }

    @Override
    public void deleteContact(ContactDTO contactDTO) {
        Contacts contacts = contactsRepository.findByEmail(contactDTO.getEmail());
        contacts.setContactState(ContactState.WAIT);
        Users users = usersRepository.findByEmail(contactDTO.getEmail());
        users.setRole(Role.ROLE_CONTACT_USER);
        contactsRepository.save(contacts);
    }

    @Override
    public PageDTO<ContactDTO> getContactsByPage(String firmName, String state, String orderBy,boolean desc, Pageable pageable) {
        Firms firms = firmsRepository.findByFirmName(firmName);
        Page<Contacts> contactsPage=null;
        Map<String,String> map=new HashMap<>();
        map.put(firmName,state);
        if(orderBy==null)
            contactsPage=contactsRepository.findAllByContactStateAndFirmIdOrderByIdDesc(ContactState.valueOf(state),firms.getId(),pageable);
        else{
            getOrderPage(map,orderBy,desc,pageable);

        }

        List<Contacts> contactsList = contactsPage.getContent();
        List<ContactDTO> contactDTOList = new ArrayList<>();
        ContactDTO contactDTO;
        for (int i = 0; i < contactsList.size(); i++) {
            if (contactsList.get(i).getContactState() == ContactState.valueOf(state)) {
                contactDTO = new ContactDTO();
                map(contactsList.get(i), contactDTO);
                contactDTOList.add(contactDTO);
            }
        }
        return getPage(contactDTOList,contactsPage);
    }
    @Override
    public Page<Contacts> getOrderPage(Map<String,String> samples, String orderBy, boolean desc,Pageable pageable) {
        Page<Contacts> contactsPage=null;
        try {
            Class persistentClass = (Class)
                    ((ParameterizedType)getClass().getGenericSuperclass())
                            .getActualTypeArguments()[0];
            Method method = FirmsServiceImpl.class.getMethod("getOrderPage",Map.class,String.class,boolean.class,Pageable.class);
            String genericReturnType = method.getGenericReturnType().getTypeName();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        String sample = (String) samples.values().toArray()[0];
        Long firmId = (Long) samples.values().toArray()[1];
        if(OrderTypes.ORDER_BY_FIRST_NAME==OrderTypes.valueOf(orderBy)){
            if(desc)
                contactsPage=contactsRepository.findAllByContactStateAndFirmIdOrderByFirstNameDesc(ContactState.valueOf(sample),firmId,pageable);
            else
                contactsPage=contactsRepository.findAllByContactStateAndFirmIdOrderByFirstName(ContactState.valueOf(sample),firmId,pageable);
        }
        return contactsPage;
    }

}
