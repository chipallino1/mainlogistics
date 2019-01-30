package com.samsolutions.logistics.mainlogistics.controllers;

import com.samsolutions.logistics.mainlogistics.dto.ContactDTO;
import com.samsolutions.logistics.mainlogistics.dto.FirmDTO;
import com.samsolutions.logistics.mainlogistics.services.signup.ContactsSignUpService;
import com.samsolutions.logistics.mainlogistics.services.signup.FirmsSignUpService;
import com.samsolutions.logistics.mainlogistics.services.signup.SignUpType;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class AuthenticationMBeanController {

    private FirmDTO firmDTO;
    private ContactDTO contactDTO;

    @Inject
    private ContactsSignUpService contactsSignUpService;
    @Inject
    private FirmsSignUpService firmsSignUpService;

    @PostConstruct
    public void init(){
        System.out.println("Post construct");
        this.firmDTO=new FirmDTO();
        this.contactDTO=new ContactDTO();
    }

    public void register(){
        System.out.println("Register");
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
}
