package com.samsolutions.logistics.mainlogistics.dao.pagination;

import com.samsolutions.logistics.mainlogistics.dao.DaoInterface;
import com.samsolutions.logistics.mainlogistics.services.utils.PageDao;
import com.samsolutions.logistics.mainlogistics.services.utils.PaginationDao;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

@Service
public class PaginationDaoImpl<T,Id extends Serializable> implements PaginationDao<T,Id> {

    private EntityManager entityManager;
    private Class<T> entityClass;

    public PaginationDaoImpl() {
        this.getClass();
    }

    @Override
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    @Override
    public void setEntityClass(Class<T> entityClass){
        this.entityClass=entityClass;
    }

    @Override
    public List<T> getPage(int pageNum,int elements) {
        return entityManager.createQuery("from "+entityClass.getTypeName()).setFirstResult(pageNum*elements).setMaxResults(elements).getResultList();
    }

    @Override
    public List getPage(int pageNum,int elements, Query query) {
        return query.setFirstResult(pageNum*elements).setMaxResults(elements).getResultList();
    }
}
