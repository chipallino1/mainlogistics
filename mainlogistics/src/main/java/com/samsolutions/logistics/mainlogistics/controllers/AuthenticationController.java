package com.samsolutions.logistics.mainlogistics.controllers;

import com.samsolutions.logistics.mainlogistics.entities.ContactsEntity;
import com.samsolutions.logistics.mainlogistics.entities.FirmsEntity;
import com.samsolutions.logistics.mainlogistics.security.SaltHash;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;

@Controller
public class AuthenticationController {

    @RequestMapping(path = "auth",method = RequestMethod.GET)
    public String authenticate( Model model){

        model.addAttribute("contactsEntity",new ContactsEntity());
        model.addAttribute("firmsEntity",new FirmsEntity());

        try {
            byte saltBytes[]=SaltHash.getSalt();
           String secureHash= SaltHash.get_SHA_256_SecurePassword("10101998егор1",saltBytes);
           if(SaltHash.validate("10101998егор",secureHash,saltBytes)){
               System.out.println("true");
           }else{
               System.out.println("fasle");
           }

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return "authentication";

    }
    @RequestMapping(path = "contact",method = RequestMethod.POST)
    public String contact(@Valid ContactsEntity contactsEntity,BindingResult bindingResult) {

        if(bindingResult.hasErrors()){

            return "authentication";
        }

        return "index";

    }

}
