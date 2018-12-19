package com.samsolutions.logistics.mainlogistics.controllers;

import com.samsolutions.logistics.mainlogistics.dto.ContactDTO;
import com.samsolutions.logistics.mainlogistics.services.security.Role;
import com.samsolutions.logistics.mainlogistics.services.user.ContactsService;
import com.samsolutions.logistics.mainlogistics.services.user.FirmsService;
import com.samsolutions.logistics.mainlogistics.services.utils.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collection;

@RestController
public class FileController {

    private FileStorageService fileStorageService;
    private ContactsService contactsService;
    private FirmsService firmsService;

    @Autowired
    public void setFileStorageService(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }
    @Autowired
    public void setContactsService(ContactsService contactsService) {
        this.contactsService = contactsService;
    }
    @Autowired
    public void setFirmsService(FirmsService firmsService) {
        this.firmsService = firmsService;
    }

    @GetMapping("/image")
    public ResponseEntity<Resource> getFile(@RequestParam String fileName,@RequestParam String email, HttpServletRequest request) {
        // Load file as Resource
        Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>)SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        String role = ((SimpleGrantedAuthority)authorities.toArray()[0]).getAuthority();
        Resource resource=null;
        if(Role.valueOf(role)==Role.ROLE_SIMPLE_FIRM_USER || Role.valueOf(role)==Role.ROLE_LOGISTIC_FIRM_USER)
            resource = fileStorageService.loadFileAsResource(fileName,firmsService.getCreatedAt(email));
        else{
            resource = fileStorageService.loadFileAsResource(fileName,contactsService.getCreatedAt(email));
        }
        HttpHeaders headers = new HttpHeaders();
        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }
        headers.set("Content-type",contentType);
        return new ResponseEntity<Resource>(resource,headers, HttpStatus.OK);
    }

}
