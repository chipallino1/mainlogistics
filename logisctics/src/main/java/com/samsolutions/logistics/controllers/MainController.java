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
        return "form";
    }

    @RequestMapping(value = {"/signup/firm"},method = RequestMethod.POST)
    public String registerFirm(ContactsEntity contactsEntity, BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            return "error1";
        }
        return "userpage";
    }
    @RequestMapping(value = {"/signup/user"},method = RequestMethod.POST)
    public String registerUser(ContactsEntity contactsEntity, BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            return "error1";
        }

        return "userpage";
    }
}
