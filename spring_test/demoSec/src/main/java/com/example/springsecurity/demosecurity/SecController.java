package com.example.springsecurity.demosecurity;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SecController {

    @RequestMapping(path = {"/","home"},method = RequestMethod.GET)
    public String getHomePage(){
        return "home";
    }
    @RequestMapping(path = "hello",method = RequestMethod.GET)
    public String getHelloPage(){
        return "hello";
    }
    @RequestMapping(path = "login",method = RequestMethod.GET)
    public String getLoginPage(){
        return "login";
    }

}
