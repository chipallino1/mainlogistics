package com.example.examplethymeleaf.controllers;

import com.example.examplethymeleaf.entities.PersonTestEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TestController {

    @RequestMapping(path ="/auth",method = RequestMethod.GET)
    public String testThymeleaf(PersonTestEntity person1) {

        return "au";

    }
    @RequestMapping(path = "/firm",method = RequestMethod.POST)
    public  String testFirm(PersonTestEntity personTest){
        return "form";
    }

}
