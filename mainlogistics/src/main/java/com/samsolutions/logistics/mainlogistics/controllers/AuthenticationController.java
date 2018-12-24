package com.samsolutions.logistics.mainlogistics.controllers;

import com.samsolutions.logistics.mainlogistics.dto.ContactDTO;
import com.samsolutions.logistics.mainlogistics.dto.FirmDTO;
import com.samsolutions.logistics.mainlogistics.dto.UserDTO;
import com.samsolutions.logistics.mainlogistics.services.signup.ContactsSignUpService;
import com.samsolutions.logistics.mainlogistics.services.signup.FirmsSignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 *  Controller class for users that want to register
 *
 */

@Controller
public class AuthenticationController {

    private ContactsSignUpService contactsSignUpService;
    private FirmsSignUpService firmsSignUpService;

    @Autowired
    public void setContactsSignUpService(ContactsSignUpService contactsSignUpService) {
        this.contactsSignUpService = contactsSignUpService;
    }

    @Autowired
    public void setFirmsSignUpService(FirmsSignUpService firmsSignUpService) {
        this.firmsSignUpService = firmsSignUpService;
    }


    /**
     * Get authentication page
     * @param model model for adding attributes
     * @param isRegistred set true when user register
     * @return view
     */
    @RequestMapping(path = "auth", method = RequestMethod.GET)
    public String getAuthenticate(Model model, @ModelAttribute("registred") String isRegistred) {

        /*if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().toArray()[0].toString().equals("ROLE_USER")){
            model.addAttribute("isRegistred",true);
        }*/
        model.addAttribute("contactDTO", new ContactDTO());
        model.addAttribute("firmDTO", new FirmDTO());
        model.addAttribute("userDTO", new UserDTO());
        model.addAttribute("firms", null);
        if (model.containsAttribute("registred")) {
            if (!model.asMap().get("registred").equals("")) {
                model.addAttribute("isRegistred", true);
            }
        }

        return "authentication";

    }

    /**
     * Redirection
     * @param userType user type
     * @return redirect to authentication page
     */
    @RequestMapping(path = "auth/{userType}", method = RequestMethod.GET)
    public String getRedirectToAuth(@PathVariable String userType) {
        return "redirect:/auth";
    }

    /**
     * Register user
     * @param contactDTO user data for register(if type is not contact user it will object with not set fields)
     * @param bindingResultContacts if contact user has error in register data
     * @param firmDTO user data for register(if type is not firm user it will object with not set fields)
     * @param bindingResultFirms if firm user has error in register data
     * @param model model for adding attributes
     * @param userType user type
     * @param redirectAttributes for adding attributes when redirection happens
     * @return redirection
     */
    @RequestMapping(path = "auth/{userType}", method = RequestMethod.POST)
    public String getRegistered(@Valid ContactDTO contactDTO, BindingResult bindingResultContacts,
                                @Valid FirmDTO firmDTO, BindingResult bindingResultFirms, Model model,
                                @PathVariable String userType, RedirectAttributes redirectAttributes) {
        model.addAttribute("userDTO", new UserDTO());
        if (userType.equals("contact")) {
            if (bindingResultContacts.hasErrors()) {
                model.addAttribute("firmDTO", new FirmDTO());
                return "authentication";
            }
            contactsSignUpService.createNewContact();
            contactsSignUpService.setContactDTO(contactDTO);
            contactsSignUpService.saveContact();
            redirectAttributes.addFlashAttribute("registred", "true");

            return "redirect:/auth";
        } else {
            if (bindingResultFirms.hasErrors()) {
                model.addAttribute("contactDTO", new ContactDTO());
                return "authentication";
            }

            firmsSignUpService.setFirmDTO(firmDTO);
            firmsSignUpService.saveFirm();
            redirectAttributes.addFlashAttribute("isRegistred", "true");
            return "redirect:/auth";
        }
    }


}
