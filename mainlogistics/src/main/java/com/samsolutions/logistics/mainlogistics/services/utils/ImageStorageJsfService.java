package com.samsolutions.logistics.mainlogistics.services.utils;

import org.springframework.stereotype.Component;

import javax.servlet.http.Part;

@Component
public interface ImageStorageJsfService extends PartFileService{
    String storeImage(Part part,String createdAt,String email);
}
