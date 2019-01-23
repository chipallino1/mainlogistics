package com.jpa.hibernate.demodao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DaoController {

    @Autowired
    private UserDAO userDAO;

    @PostMapping("/create")
    public User createUser(@RequestBody User user){
        userDAO.setEntityClass(user.getClass());
        userDAO.create(user);
        return user;
    }

}
