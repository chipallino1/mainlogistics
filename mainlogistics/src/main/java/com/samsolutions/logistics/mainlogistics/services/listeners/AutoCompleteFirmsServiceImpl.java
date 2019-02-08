package com.samsolutions.logistics.mainlogistics.services.listeners;

import com.samsolutions.logistics.mainlogistics.services.events.FirmAddingEvent;
import com.samsolutions.logistics.mainlogistics.services.user.FirmsService;
import com.samsolutions.logistics.mainlogistics.services.utils.JsonEncoder;
import com.samsolutions.logistics.mainlogistics.validation.exceptions.FileStorageException;
import com.sun.faces.facelets.util.Path;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Service
@ApplicationScoped
public class AutoCompleteFirmsServiceImpl {

    private List<String> firmsList;
    @Inject
    private FirmsService firmsService;
    @Inject
    private JsonEncoder jsonEncoder;


    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup(ApplicationReadyEvent applicationReadyEvent) {
        this.firmsList = firmsService.getAllFirmsNamesByName("");
        saveFirmsList();
        System.out.println("Autocomplete firms list loaded");
    }

    @EventListener(FirmAddingEvent.class)
    public void doSomethingAfterStartup(FirmAddingEvent firmAddingEvent) {
        this.firmsList.add(firmAddingEvent.getFirmName());
        saveFirmsList();
        System.out.println("Autocomplete firms list updated");
    }

    private void saveFirmsList(){
        String jsonFirmsList = jsonEncoder.toJson(this.firmsList);
        InputStream targetStream = new ByteArrayInputStream(jsonFirmsList.getBytes());
        try {
            Files.copy(targetStream, Paths.get("./firms.json").toAbsolutePath().normalize() , StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new FileStorageException("Could not save file!", e);
        }
    }

    public List<String> getFirmsByFirmName(String firmName){
        Stream<Object> stream = Arrays.stream(this.firmsList.toArray()).filter(str -> ((String)str).startsWith(firmName));
        Object[] objects = stream.sorted().toArray();
        List<String> firmsList=new ArrayList<>();
        for(int i=0;i<objects.length;i++){
            firmsList.add((String)objects[i]);
        }
        return firmsList;
    }

    public List<String> getFirmsList() {
        return firmsList;
    }

    public void setFirmsList(List<String> firmsList) {
        this.firmsList = firmsList;
    }

}
