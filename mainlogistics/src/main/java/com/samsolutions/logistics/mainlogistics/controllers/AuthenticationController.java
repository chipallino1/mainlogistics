package com.samsolutions.logistics.mainlogistics.controllers;

import com.samsolutions.logistics.mainlogistics.entities.ContactsEntity;
import com.samsolutions.logistics.mainlogistics.entities.FirmsEntity;
import com.samsolutions.logistics.mainlogistics.security.SaltHash;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.jws.WebParam;
import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;

@Controller
public class AuthenticationController {

    @RequestMapping(path = "auth",method = RequestMethod.GET)
    public String authenticate(Model model){

        model.addAttribute("contactsEntity",new ContactsEntity());
        model.addAttribute("firmsEntity",new FirmsEntity());

        return "authentication";

    }

    @RequestMapping(path = "firm",method = RequestMethod.POST)
    public String firm(@Valid FirmsEntity firmsEntity,BindingResult bindingResult, Model model) {

        if(bindingResult.hasErrors()){

            model.addAttribute("contactsEntity",new ContactsEntity());
            return "authentication";
        }

        return "index";

    }
    @RequestMapping(path = "auth",method = RequestMethod.POST)
    public String registered(@Valid ContactsEntity contactsEntity, BindingResult bindingResultContacts,
                             @Valid FirmsEntity firmsEntity,BindingResult bindingResultFirms, Model model) {


        if(bindingResultContacts.hasErrors()){
            model.addAttribute("firmsEntity",new FirmsEntity());
            return "authentication";
        }else if(bindingResultFirms.hasErrors()){
            model.addAttribute("contactsEntity",new ContactsEntity());
            return "authentication";
        }

        return "index";

    }

}
