package com.samsolutions.logistics.mainlogistics.services.utils.impl;

import com.samsolutions.logistics.mainlogistics.services.user.FirmsService;
import com.samsolutions.logistics.mainlogistics.services.utils.JsonEncoder;
import com.samsolutions.logistics.mainlogistics.validation.exceptions.FileStorageException;
import com.sun.faces.facelets.util.Path;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
@ApplicationScoped
public class AutoCompleteFirmsServiceImpl {

    @Inject
    private FirmsService firmsService;
    @Inject
    private JsonEncoder jsonEncoder;

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
        List<String> firmsList = firmsService.getAllFirmsNamesByName("");
        String jsonFirmsList = jsonEncoder.toJson(firmsList);
        InputStream targetStream = new ByteArrayInputStream(jsonFirmsList.getBytes());
        try {
            Files.copy(targetStream, Paths.get("./firms.json").toAbsolutePath().normalize() , StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new FileStorageException("Could not save file!", e);
        }
        System.out.println("hello world, I have just started up");
    }

    public FirmsService getFirmsService() {
        return firmsService;
    }

    public void setFirmsService(FirmsService firmsService) {
        this.firmsService = firmsService;
    }
}
