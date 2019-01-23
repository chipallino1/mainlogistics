package com.example.demo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import java.io.Serializable;
import java.util.Map;

@ManagedBean
public class UserData implements Serializable {
    private static final long serialVersionUID = 1L;
    private static Map<String,String> countryMap;
    private String data = "sample data";

    public String showResult() {
        return "result";
    }

    public void updateData(ActionEvent e) {
        data="Hello World";
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}