package com.samsolutions.logistics.mainlogistics.services.utils;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public interface FileStorageService {
    String storeFile(MultipartFile file,String createdAt,String email);
    Resource loadFileAsResource(String fileName,String createdAt);
    String updateFilePath(String avatarPath,String newEmail);
}
