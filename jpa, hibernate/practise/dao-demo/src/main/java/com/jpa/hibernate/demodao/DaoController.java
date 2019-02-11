package com.jpa.hibernate.demodao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.attribute.UserDefinedFileAttributeView;

@RestController
public class DaoController {

    @Autowired
    private UserDAO userDAO;

    @GetMapping("/create")
    public User createUser(){
        User user =new User();
        userDAO.setEntityClass(user.getClass());
        userDAO.create(user);
        return user;
    }

}
