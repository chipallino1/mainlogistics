package com.samsolutions.logistics.mainlogistics.services.utils;

import java.util.List;

public interface PageDao<T> {
    List<T> getContent();
    Long getPagesCount();
    Long getElementsOnPage();
}
