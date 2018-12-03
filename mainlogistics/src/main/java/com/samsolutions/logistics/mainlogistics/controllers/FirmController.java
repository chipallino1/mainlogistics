package com.samsolutions.logistics.mainlogistics.controllers;

import com.samsolutions.logistics.mainlogistics.dto.ContactDTO;
import com.samsolutions.logistics.mainlogistics.dto.FirmDTO;
import com.samsolutions.logistics.mainlogistics.services.user.FirmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Firm user controller class for get info about firms
 */
@Controller
public class FirmController {

    private FirmsService firmsService;

    @Autowired
    public void setFirmsService(FirmsService firmsService) {
        this.firmsService = firmsService;
    }

    /**
     * Read all firms
     * @param firmName
     * @return all firms like firm name
     */
    @RequestMapping(path = "/firms/{firmName}/readall", method = RequestMethod.GET)
    public @ResponseBody
    List<FirmDTO> readAllFirmsLike(@PathVariable(value = "firmName") String firmName) {
        return firmsService.getAllByName(firmName);
    }

    /**
     * Add contact user
     * @param object request body
     * @return true if contact user was added else exception
     */

    @RequestMapping(path = "/firms/contacts/add", method = RequestMethod.POST)
    public @ResponseBody boolean addContactToFirm(@RequestBody Object object) {
        ContactDTO contactDTO = new ContactDTO();
        contactDTO.setEmail((String) ((LinkedHashMap) object).get("email"));
        contactDTO.setFirmName((String) ((LinkedHashMap) object).get("firmName"));
        firmsService.addContact(contactDTO);
        return true;
    }

    /**
     * Delete contact user
     * @param object request body
     * @return if deleted - true,else false
     */
    @RequestMapping(path = "/firms/contacts/delete", method = RequestMethod.POST)
    public @ResponseBody
    boolean deleteContact(@RequestBody Object object) {
        ContactDTO contactDTO = new ContactDTO();
        contactDTO.setEmail((String) ((LinkedHashMap) object).get("email"));
        contactDTO.setFirmName((String) ((LinkedHashMap) object).get("firmName"));
        firmsService.deleteContact(contactDTO);
        return true;
    }

    /**
     *
     * @param firmName firm name
     * @param state contact user state (ADDED,WAIT)
     * @return contact list
     */
    @RequestMapping(path = "/contacts/readall/{firmName}/{state}", method = RequestMethod.GET)
    public @ResponseBody
    List<ContactDTO> readAllContactsFirm(@PathVariable(name = "firmName") String firmName,
                                         @PathVariable(name = "state") String state) {

        return firmsService.getContactsTop5(firmName, state);
    }

}
