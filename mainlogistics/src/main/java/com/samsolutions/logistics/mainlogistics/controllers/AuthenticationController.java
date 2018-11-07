package com.samsolutions.logistics.mainlogistics.controllers;

import com.samsolutions.logistics.mainlogistics.entities.ContactsEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AuthenticationController {

    @RequestMapping(path = "auth",method = RequestMethod.GET)
    public String authenticate(Model model){

        model.addAttribute("contactsEntity",new ContactsEntity());
        return "authentication";

    }

}
