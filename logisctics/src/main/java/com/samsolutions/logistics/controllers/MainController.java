package com.samsolutions.logistics.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

    @RequestMapping(value = {"/auth"},method = RequestMethod.GET)
    public String authenticate(){
        return "authentication";
    }
}
