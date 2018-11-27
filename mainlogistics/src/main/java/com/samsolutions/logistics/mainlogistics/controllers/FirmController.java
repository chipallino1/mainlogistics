package com.samsolutions.logistics.mainlogistics.controllers;

import com.samsolutions.logistics.mainlogistics.dto.ContactDTO;
import com.samsolutions.logistics.mainlogistics.dto.FirmDTO;
import com.samsolutions.logistics.mainlogistics.services.FirmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;

@Controller
public class FirmController {

    private FirmsService firmsService;

    @Autowired
    public void setFirmsService(FirmsService firmsService) {
        this.firmsService = firmsService;
    }

    //for autocomplete when contact person choose firm
    @RequestMapping(path = "/firms/{firmName}/readall",method = RequestMethod.GET)
    public @ResponseBody
    List<FirmDTO> readAllFirmsLike(@PathVariable(value = "firmName")String firmName){
        return firmsService.getAllByName(firmName);
    }


    //get contacts that work with current firm
    @RequestMapping(path = "/firm/contacts/readall/{firmName}",method = RequestMethod.GET)
    public @ResponseBody
    List<ContactDTO> readAllContactsFirm(@PathVariable(name = "firmName")String firmName){
        return firmsService.getContacts(firmName);
    }

    @RequestMapping(path = "/firm/contacts/delete",method = RequestMethod.POST)
    public @ResponseBody
    String deleteContact(@RequestBody Object object){
        ContactDTO contactDTO=new ContactDTO();
        contactDTO.setEmail((String)((LinkedHashMap)object).get("email"));
        contactDTO.setFirmName((String)((LinkedHashMap)object).get("firmName"));
        firmsService.deleteContact(contactDTO);
        return "deleted";
    }

}
