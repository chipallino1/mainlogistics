package com.samsolutions.logistics.mainlogistics.services.utils;

import com.samsolutions.logistics.mainlogistics.dao.DaoInterface;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.Selection;
import java.io.Serializable;
import java.util.List;

@Component
public interface PaginationDao<T,Id extends Serializable> {
    void setEntityManager(EntityManager entityManager);
    void setEntityClass(Class<T> entityClass);
    List<T> getPage(int pageNum,int elements);
    List getPage(int pageNum,int elements, Query query);
}
