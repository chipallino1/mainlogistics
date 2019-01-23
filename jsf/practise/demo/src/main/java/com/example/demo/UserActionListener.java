package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

@Service
public class UserActionListener implements ActionListener {



    @Override
    public void processAction(ActionEvent arg0)
            throws AbortProcessingException {
        ApplicationContext applicationContext = DemoApplication.applicationContext;
        //access userData bean directly
        UserData userData = (UserData) FacesContext.getCurrentInstance().
                getExternalContext().getSessionMap().get("userData");
        userData.setData("Hello World");
    }
}