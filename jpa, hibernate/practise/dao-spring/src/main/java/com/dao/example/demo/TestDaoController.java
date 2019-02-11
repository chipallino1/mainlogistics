package com.dao.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestDaoController {

    @Autowired
    private ContactsDaoImpl contactsDao;

    @GetMapping("/")
    public void get(){
        System.out.println("Hi");
    }
}
