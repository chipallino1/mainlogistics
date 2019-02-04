package com.samsolutions.logistics.mainlogistics.controllers;

import com.samsolutions.logistics.mainlogistics.dto.ContactDTO;
import com.samsolutions.logistics.mainlogistics.dto.FirmDTO;
import com.samsolutions.logistics.mainlogistics.services.signup.ContactsSignUpService;
import com.samsolutions.logistics.mainlogistics.services.signup.FirmsSignUpService;
import com.samsolutions.logistics.mainlogistics.services.signup.SignUpType;
import com.samsolutions.logistics.mainlogistics.services.utils.ImageStorageJsfService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class AuthenticationMBeanController {

    private FirmDTO firmDTO;
    private ContactDTO contactDTO;
    private List<String> list;

    @Inject
    private ContactsSignUpService contactsSignUpService;
    @Inject
    private FirmsSignUpService firmsSignUpService;
    @Inject
    private ImageStorageJsfService imageStorageJsfService;

    @PostConstruct
    public void init(){
        System.out.println("Post construct");
        this.firmDTO=new FirmDTO();
        this.contactDTO=new ContactDTO();
        this.list=new ArrayList<>();
        this.list.add("Egor");
        this.list.add("Vanya");
        this.list.add("Mama");
        this.list.add("Papa");

    }

    public void register(){
        System.out.println("Register");
        imageStorageJsfService.storeImage(contactDTO.getPartImage(),((Long)System.currentTimeMillis()).toString(),"egor");
    }

    public void getFirms(ValueChangeEvent event){
        System.out.println("Value changed");
        String newValue = (String)event.getNewValue();

    }

    public FirmDTO getFirmDTO() {
        return firmDTO;
    }

    public void setFirmDTO(FirmDTO firmDTO) {
        this.firmDTO = firmDTO;
    }

    public ContactDTO getContactDTO() {
        return contactDTO;
    }

    public void setContactDTO(ContactDTO contactDTO) {
        this.contactDTO = contactDTO;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}
