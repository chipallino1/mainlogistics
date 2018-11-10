package com.samsolutions.logistics.mainlogistics.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ProfileController {

    @RequestMapping(path = "profile/{type}",method = RequestMethod.GET)
    public String profilePage(){
        return "index";
    }

}
