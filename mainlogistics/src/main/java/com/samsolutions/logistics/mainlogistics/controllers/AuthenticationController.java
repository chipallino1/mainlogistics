package com.samsolutions.logistics.mainlogistics.controllers;

import com.samsolutions.logistics.mainlogistics.dto.ContactDTO;
import com.samsolutions.logistics.mainlogistics.dto.FirmDTO;
import com.samsolutions.logistics.mainlogistics.entities.Contacts;
import com.samsolutions.logistics.mainlogistics.entities.Firms;
import com.samsolutions.logistics.mainlogistics.entities.Passwords;
import com.samsolutions.logistics.mainlogistics.repositories.ContactsRepository;
import com.samsolutions.logistics.mainlogistics.repositories.FirmsRepository;
import com.samsolutions.logistics.mainlogistics.repositories.PasswordsRepository;
import com.samsolutions.logistics.mainlogistics.services.ContactsSignUpServiceImpl;
import com.samsolutions.logistics.mainlogistics.services.FirmsService;
import com.samsolutions.logistics.mainlogistics.services.FirmsSignUpService;
import com.samsolutions.logistics.mainlogistics.services.security.SaltHash;
import com.samsolutions.logistics.mainlogistics.services.ContactsSignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class AuthenticationController {

    private ContactsSignUpService contactsSignUpService;
    private FirmsSignUpService firmsSignUpService;
    private FirmsService firmsService;

    @Autowired
    public void setContactsSignUpService(ContactsSignUpService contactsSignUpService) {
        this.contactsSignUpService = contactsSignUpService;
    }
    @Autowired
    public void setFirmsSignUpService(FirmsSignUpService firmsSignUpService) {
        this.firmsSignUpService = firmsSignUpService;
    }
    @Autowired
    public void setFirmsService(FirmsService firmsService) {
        this.firmsService = firmsService;
    }

    @RequestMapping(path = {"/","index"},method = RequestMethod.GET)
    public String getHome(){
        return "index";
    }

    @RequestMapping(path = "auth",method = RequestMethod.GET)
    public String getAuthenticate(Model model,@ModelAttribute("isRegistred") String isRegistred){

        model.addAttribute("contactDTO",new ContactDTO());
        model.addAttribute("firmDTO",new FirmDTO());
        model.addAttribute("firms",null);
        if(!isRegistred.equals("")){
            model.addAttribute("isRegistred",true);
        }

        return "authentication";

    }

    @RequestMapping(path = "auth/{userType}",method =RequestMethod.GET)
    public String getRedirectToAuth(@PathVariable String userType){

        return "redirect:/auth";

    }

    @RequestMapping(path = "auth/{userType}",method = RequestMethod.POST)
    public String getRegistered(@Valid ContactDTO contactDTO, BindingResult bindingResultContacts,
                                @Valid FirmDTO firmDTO, BindingResult bindingResultFirms, Model model,
                                @PathVariable String userType, RedirectAttributes redirectAttributes) {

        if(userType.equals("contact")) {
            if(bindingResultContacts.hasErrors()){
                model.addAttribute("firmDTO",new FirmDTO());
                return "authentication";
            }
            contactsSignUpService.setContactDTO(contactDTO);
            contactsSignUpService.savePassword();
            contactsSignUpService.save();
            redirectAttributes.addFlashAttribute("isRegistred","true");

            return "redirect:/auth";
        }else{
            if(bindingResultFirms.hasErrors()){
                model.addAttribute("contactDTO",new ContactDTO());
                return "authentication";
            }

            firmsSignUpService.setFirmDTO(firmDTO);
            firmsSignUpService.savePassword();
            firmsSignUpService.save();

            redirectAttributes.addFlashAttribute("isRegistred","true");
            return "redirect:/auth";
        }

    }

    @RequestMapping(path = "/firms/{firmName}/readall",method = RequestMethod.GET)
    public @ResponseBody List<FirmDTO> readAllFirmsLike(@PathVariable(value = "firmName")String firmName){
        return firmsService.getAllByName(firmName);
    }

}
