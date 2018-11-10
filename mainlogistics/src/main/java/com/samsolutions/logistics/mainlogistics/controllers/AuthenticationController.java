package com.samsolutions.logistics.mainlogistics.controllers;

import com.samsolutions.logistics.mainlogistics.dto.ContactDTO;
import com.samsolutions.logistics.mainlogistics.dto.FirmDTO;
import com.samsolutions.logistics.mainlogistics.dto.UserDTO;
import com.samsolutions.logistics.mainlogistics.entities.Contacts;
import com.samsolutions.logistics.mainlogistics.entities.Firms;
import com.samsolutions.logistics.mainlogistics.entities.Passwords;
import com.samsolutions.logistics.mainlogistics.repositories.ContactsRepository;
import com.samsolutions.logistics.mainlogistics.repositories.FirmsRepository;
import com.samsolutions.logistics.mainlogistics.repositories.PasswordsRepository;
import com.samsolutions.logistics.mainlogistics.services.*;
import com.samsolutions.logistics.mainlogistics.services.security.SaltHash;
import com.samsolutions.logistics.mainlogistics.services.security.SaltHashImpl;
import org.modelmapper.ModelMapper;
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

    @RequestMapping(path = {"/","index"},method = RequestMethod.GET)
    public String getHome(){
        return "index";
    }

    @RequestMapping(path = "auth",method = RequestMethod.GET)
    public String getAuthenticate(Model model,@ModelAttribute("isRegistred") String isRegistred){

        model.addAttribute("contactDTO",new ContactDTO());
        model.addAttribute("firmDTO",new FirmDTO());
        model.addAttribute("userDTO",new UserDTO());
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
        model.addAttribute("userDTO",new UserDTO());
        if(userType.equals("contact")) {
            if(bindingResultContacts.hasErrors()){
                model.addAttribute("firmDTO",new FirmDTO());
                return "authentication";
            }
            SaltHashImpl saltHash=new SaltHashImpl();
            byte[] salt=saltHash.getSalt();
            String hash= saltHash.get_SHA_256_SecurePassword(contactDTO.getPasswordRepeat(),salt);
            if(saltHash.validate(contactDTO.getPasswordRepeat(),hash ,saltHash.getBytesFromString(saltHash.getStringFromBytes( salt)))){
                System.out.println("hui");

            }
            else{
                System.out.println("ne hui");
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

    @RequestMapping(path = "auth/login",method = RequestMethod.POST)
    public String login(@Valid UserDTO userDTO,RedirectAttributes redirectAttributes){

        if(userLogInService.authorize(userDTO.getEmail(),userDTO.getPassword())) {
            if (userLogInService.isContact()) {
                redirectAttributes.addFlashAttribute("contactDTO", userLogInService.getContactDTO());
                return "redirect:/profile/contact";
            }
            redirectAttributes.addFlashAttribute("firmDTO", userLogInService.getFirmDTO());
            return "redirect:/profile/firm";
        }
        return "error";
    }

}
