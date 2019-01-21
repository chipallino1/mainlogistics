package com.example.demo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.Part;
import java.io.Serializable;

@Scope("session")
@Component
public class RegistrationBean implements Serializable {
    private String login;
    private Part file;
    private String password;


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void getData(ActionEvent actionEvent){
        RegistrationBean user = (RegistrationBean) FacesContext.getCurrentInstance().
                getExternalContext().getSessionMap().get("registrationBean");
        System.out.println(user.getLogin()+" "+user.getPassword());
    }


}
