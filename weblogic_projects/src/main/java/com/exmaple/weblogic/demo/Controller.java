package com.exmaple.weblogic.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @GetMapping("/")
    public String getHelloWorld(){
        return "Hello world!!!";
    }
}
