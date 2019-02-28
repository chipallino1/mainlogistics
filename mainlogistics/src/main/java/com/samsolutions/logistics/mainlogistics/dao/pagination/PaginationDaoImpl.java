package com.samsolutions.logistics.mainlogistics.dao.pagination;

import com.samsolutions.logistics.mainlogistics.entities.Contacts;
import com.samsolutions.logistics.mainlogistics.services.utils.PaginationDao;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.*;
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
        Query query = entityManager.createQuery("from "+entityClass.getTypeName());
        setContentAndPagesCount(pageNum,elements,query);
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
    private Long getCountRowsByCondition(CriteriaQuery cq, Root root, Predicate... predicates){
        CriteriaBuilder qb = entityManager.getCriteriaBuilder();
        cq.select(qb.count(root));
        cq.where(predicates);
        return (Long)entityManager.createQuery(cq).getSingleResult();
    }
    private void setContentAndPagesCount(int pageNum,int elements,Query query){
        this.content = query.setFirstResult(pageNum*elements).setMaxResults(elements).getResultList();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
        Root root = criteriaQuery.from(Contacts.class);
        Long countRows = getCountRowsByCondition(criteriaQuery,root);
        long pagesCount=countRows/ (long) elements;
        if((double)countRows/(double) elements!=pagesCount)
            this.pagesCount=pagesCount+1;
        else
            this.pagesCount=pagesCount;
    }
    @Override
    public long getRowsCount(String query){
        return (long)entityManager.createQuery(query).getSingleResult();
    }
}
