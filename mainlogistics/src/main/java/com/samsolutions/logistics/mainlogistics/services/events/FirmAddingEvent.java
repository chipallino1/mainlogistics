package com.samsolutions.logistics.mainlogistics.services.events;

import com.samsolutions.logistics.mainlogistics.dto.FirmDTO;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;

public class FirmAddingEvent extends ApplicationEvent {
    private String firmName;

    public FirmAddingEvent(Object source, String firmName) {
        super(source);
        this.firmName = firmName;
    }
    public String getFirmName() {
        return firmName;
    }
}