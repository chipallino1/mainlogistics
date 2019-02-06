package com.samsolutions.logistics.mainlogistics.services.utils;

import org.springframework.util.StringUtils;

import javax.servlet.http.Part;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public interface PartFileService {
    default String createPartFile(Part part,Path fileStorageLocation,String createdAt,String email) throws IOException {
        String fileName = StringUtils.cleanPath(((Long)System.currentTimeMillis()).toString()+"-"+email);
        Path targetLocation = Paths.get(fileStorageLocation+"/"+createdAt+"/"+fileName).toAbsolutePath().normalize();
        Files.copy(part.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        return "/image?fileName="+fileName+"&email="+email;
    }
}
