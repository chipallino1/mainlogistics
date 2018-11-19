package com.samsolutions.logistics.mainlogistics.controllers;

import com.samsolutions.logistics.mainlogistics.dto.ContactDTO;
import com.samsolutions.logistics.mainlogistics.dto.FirmDTO;
import com.samsolutions.logistics.mainlogistics.services.ContactsService;
import com.samsolutions.logistics.mainlogistics.services.FirmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ProfileController {

    private FirmsService firmsService;
    private ContactsService contactsService;

    @Autowired
    public void setFirmsService(FirmsService firmsService) {
        this.firmsService = firmsService;
    }
    @Autowired
    public void setContactsService(ContactsService contactsService) {
        this.contactsService = contactsService;
    }

    @RequestMapping(path = "profile/{type}/{email}",method = RequestMethod.GET)
    public String profilePage(@PathVariable("type") String type,@PathVariable("email") String email, Model model){

        if(type.equals("contact")){
            if(email.equals("me")){
                String profileName = SecurityContextHolder.getContext().getAuthentication().getName();
                model.addAttribute("contactDTO",contactsService.getByEmail(profileName));
                model.addAttribute("firmDTO",new FirmDTO());
                model.addAttribute("profileName",profileName);
                return "myprofile";
            }else{
                model.addAttribute("contactDTO",contactsService.getByEmail(email));
                model.addAttribute("firmDTO",new FirmDTO());
                model.addAttribute("profileName",email);
                return "profile";
            }
        }else if(type.equals("firm")){
            if(email.equals("me")){
                model.addAttribute("firmDTO",firmsService.getByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
                model.addAttribute("contactDTO",new ContactDTO());
                model.addAttribute("profileName",email);
                return "myprofile";
            }else{
                model.addAttribute("firmDTO",firmsService.getByEmail(email));
                model.addAttribute("contactDTO",new ContactDTO());
                model.addAttribute("profileName",email);
                return "profile";
            }
        }
        return "error";
    }

}
