package com.samsolutions.logistics.mainlogistics.services.utils;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PageDao<T> {
    void setContent();
    List<T> getContent();
    Long getPagesCount();
    Long getElementsOnPage();
}
