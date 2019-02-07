package com.samsolutions.logistics.mainlogistics.services.events;

import org.springframework.context.ApplicationEvent;

public class FirmAddingEvent extends ApplicationEvent {
    private String message;

    public FirmAddingEvent(Object source, String message) {
        super(source);
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
}