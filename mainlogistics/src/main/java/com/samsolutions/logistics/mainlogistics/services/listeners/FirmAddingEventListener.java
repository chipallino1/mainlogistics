package com.samsolutions.logistics.mainlogistics.services.listeners;

import com.samsolutions.logistics.mainlogistics.services.events.FirmAddingEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class FirmAddingEventListener implements ApplicationListener<FirmAddingEvent> {
    @Override
    public void onApplicationEvent(FirmAddingEvent event) {
        System.out.println("Received spring custom event - " + event.getMessage());
    }

}