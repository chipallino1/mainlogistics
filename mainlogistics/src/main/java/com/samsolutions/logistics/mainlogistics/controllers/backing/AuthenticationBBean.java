package com.samsolutions.logistics.mainlogistics.controllers.backing;

import com.samsolutions.logistics.mainlogistics.services.jsf.ComponentsContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.ManagedBean;
import javax.faces.bean.NoneScoped;
import java.io.Serializable;
import java.util.Map;

@ManagedBean
public class AuthenticationBBean implements Serializable {

    private String firstName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
