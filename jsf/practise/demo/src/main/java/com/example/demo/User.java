package com.example.demo;

import org.springframework.stereotype.Component;

@Component
public class User {
    private String firstName="Egor";
    private String lastName="Skorupich";

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
