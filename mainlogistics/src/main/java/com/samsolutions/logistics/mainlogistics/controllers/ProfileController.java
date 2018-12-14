package com.samsolutions.logistics.mainlogistics.controllers;

import com.samsolutions.logistics.mainlogistics.dto.ContactDTO;
import com.samsolutions.logistics.mainlogistics.dto.FirmDTO;
import com.samsolutions.logistics.mainlogistics.services.user.ContactsService;
import com.samsolutions.logistics.mainlogistics.services.user.FirmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Profile controller for CRUD in profile page
 */
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


    /**
     * Get profile
     * @param type type of profile(contact,firm)
     * @param email email
     * @param model for adding attributes
     * @return view
     */
    @RequestMapping(path = "profile/{type}/{email}", method = RequestMethod.GET)
    public String profilePage(@PathVariable("type") String type, @PathVariable("email") String email, Model model) {
        String profileName = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("contact", new ContactDTO());
        //contact user
        if (type.equals("contact")) {
            //your profile
            if (email.equals("me")) {

                model.addAttribute("contactDTO", contactsService.getByEmail(profileName));
                model.addAttribute("firmDTO", new FirmDTO());
                model.addAttribute("profileName", profileName);
                return "myprofile";
            }
            //another profile
            else {
                model.addAttribute("contactDTO", contactsService.getByEmail(email));
                model.addAttribute("firmDTO", new FirmDTO());
                model.addAttribute("profileName", email);
                return "myprofile";
            }
        } else if (type.equals("firm")) {
            if (email.equals("me")) {
                model.addAttribute("firmDTO", firmsService.getByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
                model.addAttribute("contactDTO", new ContactDTO());
                model.addAttribute("profileName", profileName);
                return "myprofile";
            } else {
                model.addAttribute("firmDTO", firmsService.getByEmail(email));
                model.addAttribute("contactDTO", new ContactDTO());
                model.addAttribute("profileName", email);
                return "myprofile";
            }
        }
        return "error";
    }

    /**
     * Update user
     * @param userType type of profile(contact,firm)
     * @param contactDTO data for updating contact(if type==firm it will be object without set fields)
     * @param firmDTO data for updating firm(if type==contact it will be object without set fields)
     * @return redirection to profile page
     */
    @RequestMapping(path = "profile/{type}/update", method = RequestMethod.POST)
    public String updateUser(@PathVariable("type") String userType, ContactDTO contactDTO, FirmDTO firmDTO) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        //for contact
        if (userType.equals("contact")) {
            contactsService.update(email, contactDTO);
            //logout if you change email
            if (contactDTO.getEmail().equals(email))
                return "redirect:/profile/contact/me";
            else
                return "redirect:/logout";
        }
        else {
            firmsService.update(email, firmDTO);
            if (firmDTO.getEmail().equals(email))
                return "redirect:/profile/firm/me";
            else
                return "redirect:/logout";
        }

    }

}
