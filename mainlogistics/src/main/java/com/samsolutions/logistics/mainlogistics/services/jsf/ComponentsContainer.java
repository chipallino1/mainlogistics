package com.samsolutions.logistics.mainlogistics.services.jsf;

import org.springframework.stereotype.Component;

import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

@Component
public interface ComponentsContainer {
    default UIComponent getElementById(String id){
        UIViewRoot view = FacesContext.getCurrentInstance().getViewRoot();
        UIComponent component = view.findComponent(id);
        return component;
    }
}
