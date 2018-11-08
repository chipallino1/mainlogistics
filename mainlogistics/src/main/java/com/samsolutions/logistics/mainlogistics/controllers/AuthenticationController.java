package com.samsolutions.logistics.mainlogistics.controllers;

import com.samsolutions.logistics.mainlogistics.dto.ContactDTO;
import com.samsolutions.logistics.mainlogistics.dto.FirmDTO;
import com.samsolutions.logistics.mainlogistics.repositories.ContactsRepository;
import com.samsolutions.logistics.mainlogistics.repositories.FirmsRepository;
import com.samsolutions.logistics.mainlogistics.repositories.PasswordsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class AuthenticationController {

    private ContactsRepository contactsRepository;
    private FirmsRepository firmsRepository;
    private PasswordsRepository passwordsRepository;

    @Autowired
    public void setContactsRepository(ContactsRepository contactsRepository) {
        this.contactsRepository = contactsRepository;
    }
    @Autowired
    public void setFirmsRepository(FirmsRepository firmsRepository) {
        this.firmsRepository = firmsRepository;
    }
    @Autowired
    public void setPasswordsRepository(PasswordsRepository passwordsRepository) {
        this.passwordsRepository = passwordsRepository;
    }

    @RequestMapping(path = "auth",method = RequestMethod.GET)
    public String authenticate(Model model){

        model.addAttribute("contactDTO",new ContactDTO());
        model.addAttribute("firmDTO",new FirmDTO());

        return "authentication";

    }

    @RequestMapping(path = "auth/{userType}",method =RequestMethod.GET)
    public String redirectToAuth(@PathVariable String userType){

        return "redirect:/auth";

    }

    @RequestMapping(path = "auth/{userType}",method = RequestMethod.POST)
    public String registered(@Valid ContactDTO contactDTO, BindingResult bindingResultContacts,
                             @Valid FirmDTO firmDTO, BindingResult bindingResultFirms, Model model,
                             @PathVariable String userType) {

        if(userType.equals("contact")) {
            if(bindingResultContacts.hasErrors()){
                model.addAttribute("firmDTO",new FirmDTO());
                return "authentication";
            }

            return "index";
        }else{
            if(bindingResultFirms.hasErrors()){
                model.addAttribute("contactDTO",new ContactDTO());
                return "authentication";
            }

            return "index";
        }

    }

}
