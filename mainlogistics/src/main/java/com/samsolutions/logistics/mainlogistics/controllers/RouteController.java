package com.samsolutions.logistics.mainlogistics.controllers;

import com.samsolutions.logistics.mainlogistics.dto.RouteDTO;
import com.samsolutions.logistics.mainlogistics.services.user.ContactsService;
import com.samsolutions.logistics.mainlogistics.services.utils.DateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@RestController
public class RouteController {

    @Autowired
    DateConverter dateConverter;
    @Autowired
    private ContactsService contactsService;


    @PostMapping("/routes/create")
    public boolean createRoute(@RequestBody Map<String, Object> payloadRouteDTO){

        DateFormat formatter;
        formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZ");
        Date date = null;
        contactsService.mapPayload(RouteDTO.class,payloadRouteDTO);

        return true;
    }

}
