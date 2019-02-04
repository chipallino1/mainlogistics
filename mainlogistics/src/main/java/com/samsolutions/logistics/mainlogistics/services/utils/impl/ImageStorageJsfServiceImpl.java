package com.samsolutions.logistics.mainlogistics.services.utils.impl;

import com.samsolutions.logistics.mainlogistics.properties.FileStorageProperties;
import com.samsolutions.logistics.mainlogistics.services.utils.ImageStorageJsfService;
import com.samsolutions.logistics.mainlogistics.validation.exceptions.FileStorageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.mail.internet.ContentType;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ImageStorageJsfServiceImpl implements ImageStorageJsfService {

    private Path fileStorageLocation;

    @Autowired
    public ImageStorageJsfServiceImpl(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            DateFormat dateFormat=new SimpleDateFormat("yyyy/MM");
            String folderName=dateFormat.format(new Date());
            folderName=this.fileStorageLocation+"/"+folderName;
            Files.createDirectories(Paths.get(folderName).toAbsolutePath().normalize());
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    @Override
    public String storeImage(Part part, String createdAt, String email) {
        String fileName = StringUtils.cleanPath(((Long)System.currentTimeMillis()).toString()+part.getSubmittedFileName());
        try {
            if(part.getContentType().equals(MediaType.IMAGE_GIF_VALUE) || part.getContentType().equals(MediaType.IMAGE_JPEG_VALUE) || part.getContentType().equals(MediaType.IMAGE_PNG_VALUE)) {
                Path targetLocation = Paths.get(this.fileStorageLocation+"/2019/02/"+fileName).toAbsolutePath().normalize();
                //Files.createFile(targetLocation);
                Files.copy(part.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

                return "/image?fileName="+fileName+"&email="+email;

            }
            else{
                throw new FileStorageException("Sorry! File is not an image");
            }

        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }
}
