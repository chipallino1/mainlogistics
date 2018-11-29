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

/**
 * Contact user controller class for get info about contact users
 */

@Controller
public class ContactController {

    private ContactsService contactsService;

    @Autowired
    public void setContactsService(ContactsService contactsService) {
        this.contactsService = contactsService;
    }
    /**
     *
     * @param firmName
     * @param status
     * @return contact list
     */
    @RequestMapping(path = "/contacts/readall/{firmName}/{status}", method = RequestMethod.GET)
    public @ResponseBody
    List<ContactDTO> readAllContactsFirm(@PathVariable(name = "firmName") String firmName,
                                         @PathVariable(name = "status") String status) {

        return contactsService.getContactsTop5(firmName, status);
    }


}
