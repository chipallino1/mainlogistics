package com.samsolutions.logistics.mainlogistics.controllers;

import com.samsolutions.logistics.mainlogistics.dto.RouteDTO;
import com.samsolutions.logistics.mainlogistics.services.utils.DateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class RouteController {

    @Autowired
    DateConverter dateConverter;



    @PostMapping("/routes/create")
    public boolean createRoute(RouteDTO routeDTO){

        DateFormat formatter;
        formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZ");
        Date date = null;
        try {
            date = (Date) formatter.parse(routeDTO.getDateStart());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        java.sql.Timestamp timeStampDate = new java.sql.Timestamp(date.getTime());
        dateConverter.getDateFromString("");
        return true;
    }

}
