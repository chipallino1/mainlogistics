package com.samsolutions.logistics.mainlogistics.services.utils;

import org.springframework.stereotype.Component;
import javax.persistence.Query;
import java.util.List;

@Component
public interface PaginationDao {
    void setEntityClassAndIdType(Class entityClass,Class idType);
    PaginationDao getPage(int pageNum,int elements);
    List getPage(int pageNum,int elements, Query query);
    List getContent();
    Long getPagesCount();
    Long getElementsOnPage();
}
