package com.samsolutions.logistics.mainlogistics.controllers;

import com.samsolutions.logistics.mainlogistics.dto.ContactDTO;
import com.samsolutions.logistics.mainlogistics.dto.FirmDTO;
import com.samsolutions.logistics.mainlogistics.dto.PageDTO;
import com.samsolutions.logistics.mainlogistics.dto.RouteDTO;
import com.samsolutions.logistics.mainlogistics.services.routes.RoutesService;
import com.samsolutions.logistics.mainlogistics.services.user.ContactsService;
import com.samsolutions.logistics.mainlogistics.services.user.FirmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SearchController {

    private RoutesService routesService;
    private FirmsService firmsService;
    private ContactsService contactsService;

    @Autowired
    public void setRoutesService(RoutesService routesService) {
        this.routesService = routesService;
    }

    @Autowired
    public void setFirmsService(FirmsService firmsService) {
        this.firmsService = firmsService;
    }
    @Autowired
    public void setContactsService(ContactsService contactsService) {
        this.contactsService = contactsService;
    }

    @GetMapping("/search/routes")
    public PageDTO<RouteDTO> searchRoutes(@PageableDefault(value = 5) Pageable pageable, @RequestParam("countryFrom") String coutryFrom, @RequestParam("cityFrom")String cityFrom,
                                          @RequestParam("countryTo") String countryTo, @RequestParam("cityTo")String cityTo){
        return routesService.searchRoutesByParams(coutryFrom,cityFrom,countryTo,cityTo,pageable);
    }
    @GetMapping("/search/firms")
    public PageDTO<FirmDTO> searchFirms(@PageableDefault(value = 5) Pageable pageable, @RequestParam("firmName") String firmName){
        return firmsService.getFirmsByFirmNameAndPage(firmName,pageable);
    }
    @GetMapping("/search/contacts")
    public PageDTO<ContactDTO> searchContacts(@PageableDefault(value = 5) Pageable pageable,@RequestParam("email") String email){
        return contactsService.getContactsByPageAndEmail(email,pageable);
    }
}
