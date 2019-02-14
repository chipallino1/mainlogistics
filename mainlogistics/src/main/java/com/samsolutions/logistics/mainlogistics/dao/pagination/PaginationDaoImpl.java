package com.samsolutions.logistics.mainlogistics.dao.pagination;

import com.samsolutions.logistics.mainlogistics.services.utils.PaginationDao;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.EntityType;
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
    public PaginationDao getPage(int pageNum,int elements) throws NoSuchFieldException {
        Query query = entityManager.createQuery("from "+entityClass.getTypeName());
        setContentAndPagesCount(pageNum,elements,query);
        CriteriaBuilder cb=entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = cb.createQuery(entityClass);
        Root root = criteriaQuery.from(entityClass);
        String fieldName = entityClass.getDeclaredField("email").getName();
        getCountRowsByCondition(cb.equal(root.get(fieldName),"sasha@mail.ru")/*,cb.like(root.get("firstName"),"f")*/);
        return this;
    }

    @Override
    public PaginationDao getPage(int pageNum,int elements, Query query) {
        setContentAndPagesCount(pageNum,elements,query);
        return this;
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

    private Long getCountRows(){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = cb.createQuery();
        criteriaQuery.select(cb.count(criteriaQuery.from(entityClass)));
        return (Long)entityManager.createQuery(criteriaQuery).getSingleResult();
    }
    private Long getCountRowsByCondition(Predicate... predicates){
      /* *//* CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = cb.createQuery();
        criteriaQuery.select(cb.count(criteriaQuery.where(predicates[0]).from(entityClass)));
        criteriaQuery.where(predicates);*//*
        return (Long)entityManager.createQuery(criteriaQuery).getSingleResult();*/
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        //cb.count(cq.from(entityClass));
        ParameterExpression<Integer> p =  cb.parameter(Integer.class);
        Root root = cq.from(entityClass);
        cq.select(cb.count(cq.from(entityClass)));
        cq.where(cb.equal(root.get("email"), "sasha@mail.ru"));
        return (Long)entityManager.createQuery(cq).getSingleResult();
    }
    private void setContentAndPagesCount(int pageNum,int elements,Query query){
        this.content = query.setFirstResult(pageNum*elements).setMaxResults(elements).getResultList();
        Long countRows = getCountRows();
        long pagesCount=countRows/ (long) elements;
        if((double)this.content.size()/(double) elements!=(long)this.content.size()/elements)
            this.pagesCount=pagesCount+1;
        else
            this.pagesCount=pagesCount;
    }
}
