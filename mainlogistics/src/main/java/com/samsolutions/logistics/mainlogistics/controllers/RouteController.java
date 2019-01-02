package com.samsolutions.logistics.mainlogistics.controllers;

import com.samsolutions.logistics.mainlogistics.services.utils.DateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class RouteController {

    @Autowired
    DateConverter dateConverter;



    @PostMapping("/routes/create")
    public boolean createRoute(){

        dateConverter.getDateFromString("");
        return true;
    }

}
