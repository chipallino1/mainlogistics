package com.samsolutions.logistics.mainlogistics.controllers;

import com.samsolutions.logistics.mainlogistics.dto.ContactDTO;
import com.samsolutions.logistics.mainlogistics.dto.FirmDTO;
import com.samsolutions.logistics.mainlogistics.entities.Contacts;
import com.samsolutions.logistics.mainlogistics.entities.Passwords;
import com.samsolutions.logistics.mainlogistics.repositories.ContactsRepository;
import com.samsolutions.logistics.mainlogistics.repositories.FirmsRepository;
import com.samsolutions.logistics.mainlogistics.repositories.PasswordsRepository;
import com.samsolutions.logistics.mainlogistics.services.ContactsSignUpServiceImpl;
import com.samsolutions.logistics.mainlogistics.services.security.SaltHash;
import com.samsolutions.logistics.mainlogistics.services.ContactsSignUpService;
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

    private ContactsSignUpServiceImpl contactsSignUpService;

    @Autowired
    public void setContactsSignUpService(ContactsSignUpServiceImpl contactsSignUpService) {
        this.contactsSignUpService = contactsSignUpService;
    }

    @RequestMapping(path = {"/","index"},method = RequestMethod.GET)
    public String getHome(){
        return "index";
    }

    @RequestMapping(path = "auth",method = RequestMethod.GET)
    public String getAuthenticate(Model model){

        model.addAttribute("contactDTO",new ContactDTO());
        model.addAttribute("firmDTO",new FirmDTO());

        return "authentication";

    }

    @RequestMapping(path = "auth/{userType}",method =RequestMethod.GET)
    public String getRedirectToAuth(@PathVariable String userType){

        return "redirect:/auth";

    }

    @RequestMapping(path = "auth/{userType}",method = RequestMethod.POST)
    public String getRegistered(@Valid ContactDTO contactDTO, BindingResult bindingResultContacts,
                             @Valid FirmDTO firmDTO, BindingResult bindingResultFirms, Model model,
                             @PathVariable String userType) {

        if(userType.equals("contact")) {
            if(bindingResultContacts.hasErrors()){
                model.addAttribute("firmDTO",new FirmDTO());
                return "authentication";
            }

            contactsSignUpService.setContactDTO(contactDTO);
            contactsSignUpService.savePassword();
            contactsSignUpService.save();

            return "redirect:/index";
        }else{
            if(bindingResultFirms.hasErrors()){
                model.addAttribute("contactDTO",new ContactDTO());
                return "authentication";
            }

            return "redirect:/index";
        }

    }

}
