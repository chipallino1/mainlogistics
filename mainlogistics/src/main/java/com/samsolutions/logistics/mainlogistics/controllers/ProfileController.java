package com.samsolutions.logistics.mainlogistics.controllers;

import com.samsolutions.logistics.mainlogistics.dto.ContactDTO;
import com.samsolutions.logistics.mainlogistics.dto.FirmDTO;
import com.samsolutions.logistics.mainlogistics.dto.RouteDTO;
import com.samsolutions.logistics.mainlogistics.services.user.ContactsService;
import com.samsolutions.logistics.mainlogistics.services.user.FirmsService;
import com.samsolutions.logistics.mainlogistics.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * Profile controller for CRUD in profile page
 */
@Controller
public class ProfileController {

    private FirmsService firmsService;
    private ContactsService contactsService;
    private UserService userService;

    @Autowired
    public void setFirmsService(FirmsService firmsService) {
        this.firmsService = firmsService;
    }

    @Autowired
    public void setContactsService(ContactsService contactsService) {
        this.contactsService = contactsService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
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
        model.addAttribute("routeDTO",new RouteDTO());
        if(!model.containsAttribute("error"))
            model.addAttribute("error",false);
        //contact user
        if (type.equals("contact")) {
            //your profile
            if (email.equals("me")) {
                model.addAttribute("contactDTO", contactsService.getByEmail(profileName));
                model.addAttribute("role",userService.getRoleByEmail(profileName).ordinal());
                model.addAttribute("contactState",contactsService.getContactState(profileName).ordinal());
                model.addAttribute("added",1);
                model.addAttribute("profileName", profileName);
            }
            //another profile
            else {
                model.addAttribute("contactDTO", contactsService.getByEmail(email));
                model.addAttribute("role",userService.getRoleByEmail(email).ordinal());
                model.addAttribute("contactState",0);
                model.addAttribute("added",1);
                model.addAttribute("profileName", email);
            }
            model.addAttribute("firmDTO", new FirmDTO());
            return "myprofile";
        }
        else if (type.equals("firm")) {
            if (email.equals("me")) {
                model.addAttribute("firmDTO", firmsService.getByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
                model.addAttribute("role",userService.getRoleByEmail(profileName).ordinal());
                model.addAttribute("contactState",0);
                model.addAttribute("added",1);
                model.addAttribute("profileName", profileName);

            }
            else {
                model.addAttribute("firmDTO", firmsService.getByEmail(email));
                model.addAttribute("role",userService.getRoleByEmail(email).ordinal());
                model.addAttribute("contactState",0);
                model.addAttribute("added",1);
                model.addAttribute("profileName", email);
            }
            model.addAttribute("contactDTO", new ContactDTO());
            return "myprofile";
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
    public String updateUser(@Valid ContactDTO contactDTO, BindingResult bindingResultContacts, @PathVariable("type") String userType,
                             @Valid FirmDTO firmDTO, BindingResult bindingResultFirms, RedirectAttributes redirectAttributes) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        //for contact
        if (userType.equals("contact")) {
            if(bindingResultContacts.hasErrors()){
                redirectAttributes.addFlashAttribute("error",true);
                return "redirect:/profile/contact/me";
            }
            contactsService.update(email, contactDTO);
            //logout if you change email
            if (contactDTO.getEmail().equals(email))
                return "redirect:/profile/contact/me";
            else
                return "redirect:/auth/logout";
        }
        else {
            if(bindingResultFirms.hasErrors()){
                redirectAttributes.addFlashAttribute("error",true);
                return "redirect:/profile/firm/me";
            }
            firmsService.update(email, firmDTO);
            if (firmDTO.getEmail().equals(email))
                return "redirect:/profile/firm/me";
            else
                return "redirect:/auth/logout";
        }

    }

}
