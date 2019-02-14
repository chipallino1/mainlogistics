package com.samsolutions.logistics.mainlogistics.dao.pagination;

import com.samsolutions.logistics.mainlogistics.services.utils.PaginationDao;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Service
public class PaginationDaoImpl implements PaginationDao {

    @PersistenceContext
    private EntityManager entityManager;
    private Class entityClass;
    private Class idType;
    private List content;
    private Long pagesCount;
    private Long elementsOnPage;

    public PaginationDaoImpl(List content,Long pagesCount,Long elementsOnPage) {
        this.content=content;
        this.pagesCount=pagesCount;
        this.elementsOnPage=elementsOnPage;
    }

    public PaginationDaoImpl(){}

    @Override
    public void setEntityClassAndIdType(Class entityClass,Class idType){
        this.entityClass=entityClass;
        this.idType=idType;
    }

    @Override
    public PaginationDao getPage(int pageNum,int elements) {
        this.content = entityManager.createQuery("from "+entityClass.getTypeName()).setFirstResult(pageNum*elements).setMaxResults(elements).getResultList();
        Long countRows = getCountRows();
        long pagesCount=countRows/ (long) elements;
        if((double)this.content.size()/(double) elements!=(long)this.content.size()/elements)
            this.pagesCount=pagesCount+1;
        else
            this.pagesCount=pagesCount;
        return this;
    }

    @Override
    public List getPage(int pageNum,int elements, Query query) {
        return query.setFirstResult(pageNum*elements).setMaxResults(elements).getResultList();
    }

    @Override
    public List getContent() {
        return this.content;
    }

    @Override
    public Long getPagesCount() {
        return this.pagesCount;
    }

    @Override
    public Long getElementsOnPage() {
        return this.elementsOnPage;
    }

    public Long getCountRows(){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = cb.createQuery();
        criteriaQuery.select(cb.count(criteriaQuery.from(entityClass)));
        return (Long)entityManager.createQuery(criteriaQuery).getSingleResult();
    }
}
