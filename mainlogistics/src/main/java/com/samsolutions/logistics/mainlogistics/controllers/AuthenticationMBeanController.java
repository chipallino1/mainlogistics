package com.samsolutions.logistics.mainlogistics.controllers;

import com.google.gson.Gson;
import com.samsolutions.logistics.mainlogistics.dto.ContactDTO;
import com.samsolutions.logistics.mainlogistics.dto.FirmDTO;
import com.samsolutions.logistics.mainlogistics.services.signup.ContactsSignUpService;
import com.samsolutions.logistics.mainlogistics.services.signup.FirmsSignUpService;
import com.samsolutions.logistics.mainlogistics.services.signup.SignUpType;
import com.samsolutions.logistics.mainlogistics.services.user.FirmsService;
import com.samsolutions.logistics.mainlogistics.services.utils.ImageStorageJsfService;
import com.samsolutions.logistics.mainlogistics.services.utils.JsonEncoder;
import org.primefaces.PrimeFaces;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Named
@ViewScoped
public class AuthenticationMBeanController {

    private FirmDTO firmDTO;
    private ContactDTO contactDTO;
    private List<String> firmsNamesList;
    private String json;

    @Inject
    private ContactsSignUpService contactsSignUpService;
    @Inject
    private FirmsSignUpService firmsSignUpService;
    @Inject
    private FirmsService firmsService;
    @Inject
    private JsonEncoder jsonEncoder;


    @PostConstruct
    public void init(){
        System.out.println("Post construct");
        this.firmDTO=new FirmDTO();
        this.contactDTO=new ContactDTO();

    }

    public void register(){
        System.out.println("Register");
        Map<String, String> params = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap();
        String signUpType = params.get("signUpType");
        if(SignUpType.valueOf(signUpType).equals(SignUpType.CONTACT)){
            contactsSignUpService.store(contactDTO);
        }
        else {
            firmsSignUpService.store(firmDTO);
        }
    }

    public void getFirms(AjaxBehaviorEvent event){
        System.out.println("Value changed");
        String firmName = (String)((UIInput)event.getSource()).getValue();
        this.firmsNamesList = firmsService.getAllFirmsNamesByName(firmName);
        this.json=jsonEncoder.toJson(this.firmsNamesList);
        RequestContext requestContext=RequestContext.getCurrentInstance();
        PrimeFaces.current().ajax().update("contactInfo:autocompleteForm:myInput");
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

    public List<String> getFirmsNamesList() {
        return firmsNamesList;
    }

    public void setFirmsNamesList(List<String> firmsNamesList) {
        this.firmsNamesList = firmsNamesList;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }
}
