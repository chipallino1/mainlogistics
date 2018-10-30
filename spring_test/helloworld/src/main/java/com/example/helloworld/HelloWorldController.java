package com.example.helloworld;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @RequestMapping(value = "/get")
    public String sayHelloWorld(@RequestParam(value = "string") String name)
    {
        return "Hello "+name+"!!!";
    }

}
