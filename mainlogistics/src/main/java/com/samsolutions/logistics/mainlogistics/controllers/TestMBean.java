package com.samsolutions.logistics.mainlogistics.controllers;

import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

@ManagedBean
public class TestMBean {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String processAction(){
        UIViewRoot view = FacesContext.getCurrentInstance().getViewRoot();
        UIComponent component = view.findComponent("uf:name");
        component.getAttributes().put("value", "John");
        return null;
    }

}
