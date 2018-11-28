package com.samsolutions.logistics.mainlogistics.controllers;

import com.samsolutions.logistics.mainlogistics.dto.ContactDTO;
import com.samsolutions.logistics.mainlogistics.services.user.ContactsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ContactController {

    private ContactsService contactsService;

    @Autowired
    public void setContactsService(ContactsService contactsService) {
        this.contactsService = contactsService;
    }

    //get all contacts when firm nned to add new contact
    @RequestMapping(path = "/contacts/{email}/{firmName}/readall", method = RequestMethod.GET)
    public @ResponseBody
    List<ContactDTO> readAllEmailsLike(@PathVariable(value = "email") String email, @PathVariable(value = "firmName") String firmName) {
        return contactsService.getTop5ByEmailAndStatus(email);
    }
}
