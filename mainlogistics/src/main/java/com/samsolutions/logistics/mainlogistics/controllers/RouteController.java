package com.samsolutions.logistics.mainlogistics.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class RouteController {

    @PostMapping("/routes/create")
    public boolean createRoute(){

        return true;
    }

}
