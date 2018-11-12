package com.samsolutions.logistics.mainlogistics.controllers;

import com.samsolutions.logistics.mainlogistics.dto.ContactDTO;
import com.samsolutions.logistics.mainlogistics.dto.FirmDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ProfileController {

    @RequestMapping(path = "profile/{type}",method = RequestMethod.GET)
    public String profilePage(Model model,@ModelAttribute("contactDTO")ContactDTO contactDTO, @ModelAttribute("firmDTO")FirmDTO firmDTO){
        if(contactDTO.getEmail()!=null){
            model.addAttribute("profileName",contactDTO.getFirstName()+" "+ contactDTO.getLastName());
            return "profile";
        }
        else{
            model.addAttribute("profileName",firmDTO.getFirmName());
            return "profile";
        }
    }

}
