package com.samsolutions.logistics.controllers;

import com.samsolutions.logistics.entities.ContactsEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.naming.Binding;

@Controller
public class MainController {

    @RequestMapping(value = {"/auth"},method = RequestMethod.GET)
    public String authenticationPage(){
        return "authentication";
    }

    @RequestMapping(value = {"/signup/{type}"},method = RequestMethod.POST)
    public String register(ContactsEntity contactsEntity, BindingResult bindingResult){

        return "userpage";
    }
}
