package com.example.demo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.Serializable;


@ManagedBean
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
        System.out.println("get data");
        RegistrationBean user = (RegistrationBean) FacesContext.getCurrentInstance().
                getExternalContext().getSessionMap().get("registrationBean");
        System.out.println(user.getLogin()+" "+user.getPassword());
    }
    public void uploadFile(){
        try{
            InputStream input=file.getInputStream();
            File f=new File("/Users/muradimanbayli/Desktop/upload/image.jpg");
            if(!f.exists()){
                f.createNewFile();
            }
            FileOutputStream output=new FileOutputStream(f);
            byte[] buffer=new byte[1024];
            int length;
            while((length=input.read(buffer))>0){
                output.write(buffer, 0, length);
            }

            input.close();
            output.close();
        }catch(Exception e){
            e.printStackTrace(System.out);
        }
    }


}
