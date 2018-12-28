package com.samsolutions.logistics.mainlogistics.services.utils.impl;

import com.samsolutions.logistics.mainlogistics.properties.FileStorageProperties;
import com.samsolutions.logistics.mainlogistics.services.utils.FileStorageService;
import com.samsolutions.logistics.mainlogistics.validation.exceptions.FileStorageException;
import com.samsolutions.logistics.mainlogistics.validation.exceptions.MyFileNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class FileStorageServiceImpl implements FileStorageService{

    private Path fileStorageLocation;

    @Autowired
    public FileStorageServiceImpl(FileStorageProperties fileStorageProperties) {
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
    public String storeFile(MultipartFile file,String createdAt,String email) {
        String fileName = StringUtils.cleanPath(System.currentTimeMillis()+file.getOriginalFilename());

        try {
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }
            Path targetLocation = Paths.get(this.fileStorageLocation+"/"+createdAt+"/"+fileName).toAbsolutePath().normalize();
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return "/image?fileName="+fileName+"&email="+email;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    @Override
    public Resource loadFileAsResource(String fileName,String createdAt) {
        try {
            Path filePath =Paths.get(this.fileStorageLocation+"/"+createdAt+"/"+fileName).toAbsolutePath().normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            }
            else {
                throw new MyFileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("File not found " + fileName, ex);
        }
    }

    @Override
    public String updateFilePath(String avatarPath,String newEmail) {
        String newAvatarPath = avatarPath.substring(0,avatarPath.lastIndexOf('=')+1);
        newAvatarPath = newAvatarPath + newEmail;
        return newAvatarPath;
    }
}