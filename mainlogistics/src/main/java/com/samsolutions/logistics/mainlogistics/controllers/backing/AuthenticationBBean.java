package com.samsolutions.logistics.mainlogistics.controllers.backing;

import com.samsolutions.logistics.mainlogistics.services.jsf.ComponentsContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.NoneScoped;
import java.io.Serializable;
import java.util.Map;

@Component
public class AuthenticationBBean implements Serializable {

    @Autowired
    private ComponentsContainer componentsContainer;

    public void show(){
        Map<String,Object> attributesHide = componentsContainer.getElementById("id").getAttributes();
        Map<String,Object> attributesShow = componentsContainer.getElementById("login").getAttributes();
        attributesHide.putIfAbsent("style", "display: none;");
        attributesShow.remove("style");
    }
}
