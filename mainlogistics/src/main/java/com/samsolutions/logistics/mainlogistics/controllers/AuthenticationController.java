package com.samsolutions.logistics.mainlogistics.controllers;

import com.samsolutions.logistics.mainlogistics.dto.ContactDTO;
import com.samsolutions.logistics.mainlogistics.dto.FirmDTO;
import com.samsolutions.logistics.mainlogistics.dto.UserDTO;
import com.samsolutions.logistics.mainlogistics.entities.Contacts;
import com.samsolutions.logistics.mainlogistics.repositories.ContactsRepository;
import com.samsolutions.logistics.mainlogistics.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

//controller for users that want to registred

@Controller
public class AuthenticationController {

    private ContactsSignUpService contactsSignUpService;
    private FirmsSignUpService firmsSignUpService;
    private FirmsService firmsService;
    private UserLogInService userLogInService;

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

    @Autowired
    public void setUserLogInService(UserLogInService userLogInService) {
        this.userLogInService = userLogInService;
    }



    //get authorithation page
    @RequestMapping(path = "auth",method = RequestMethod.GET)
    public String getAuthenticate(Model model,@ModelAttribute("registred") String isRegistred){

        /*if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().toArray()[0].toString().equals("ROLE_USER")){
            model.addAttribute("isRegistred",true);
        }*/
        model.addAttribute("contactDTO",new ContactDTO());
        model.addAttribute("firmDTO",new FirmDTO());
        model.addAttribute("userDTO",new UserDTO());
        model.addAttribute("firms",null);
        if(model.containsAttribute("registred")) {
            if (!model.asMap().get("registred").equals("")) {
                model.addAttribute("isRegistred", true);
            }
        }

        return "authentication";

    }

    //redirect to auth
    @RequestMapping(path = "auth/{userType}",method =RequestMethod.GET)
    public String getRedirectToAuth(@PathVariable String userType){

        return "redirect:/auth";

    }

    //registration of user
    @RequestMapping(path = "auth/{userType}",method = RequestMethod.POST)
    public String getRegistered(@Valid ContactDTO contactDTO, BindingResult bindingResultContacts,
                                @Valid FirmDTO firmDTO, BindingResult bindingResultFirms, Model model,
                                @PathVariable String userType, RedirectAttributes redirectAttributes) {
        model.addAttribute("userDTO",new UserDTO());
        if(userType.equals("contact")) {
            if(bindingResultContacts.hasErrors()){
                model.addAttribute("firmDTO",new FirmDTO());
                return "authentication";
            }

            contactsSignUpService.setContactDTO(contactDTO);
            contactsSignUpService.savePassword();
            contactsSignUpService.save();
            contactsSignUpService.saveUser();
            redirectAttributes.addFlashAttribute("registred","true");

            return "redirect:/auth";
        }else{
            if(bindingResultFirms.hasErrors()){
                model.addAttribute("contactDTO",new ContactDTO());
                return "authentication";
            }

            firmsSignUpService.setFirmDTO(firmDTO);
            firmsSignUpService.savePassword();
            firmsSignUpService.save();
            firmsSignUpService.saveUser();

            redirectAttributes.addFlashAttribute("isRegistred","true");
            return "redirect:/auth";
        }

    }

    //for autocomplete when contact person choose firm
    @RequestMapping(path = "/firms/{firmName}/readall",method = RequestMethod.GET)
    public @ResponseBody List<FirmDTO> readAllFirmsLike(@PathVariable(value = "firmName")String firmName){
        return firmsService.getAllByName(firmName);
    }


}
