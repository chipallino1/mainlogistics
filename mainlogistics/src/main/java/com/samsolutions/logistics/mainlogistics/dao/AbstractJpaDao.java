package com.samsolutions.logistics.mainlogistics.dao;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.io.Serializable;
import java.util.List;

@Transactional
public abstract class AbstractJpaDao<T,Id extends Serializable> implements DaoInterface<T,Id> {

    private Class<T> entityClass;
    public abstract EntityManager getEntityManager() ;

    public final void setEntityClass( Class<T> entityClass ){
        this.entityClass = entityClass;
    }

    @Override
    public void persist(T entity) {
        getEntityManager().persist(entity);
    }

    @Override
    public void update(T entity) {
        getEntityManager().merge(entity);
    }

    @Override
    public T findById(Id id) {
        return findBy("id",id);
    }

    @Override
    public void delete(T entity) {
        getEntityManager().remove(entity);
    }

    @Override
    public List<T> findAll() {
        return getEntityManager().createQuery("from "+entityClass).getResultList();
    }

    protected T findBy(String by,Object object){
        T returnEntity=null;
        List<T> tList = createCriteriaQuery(by,object).getResultList();
        if(tList.size()>0){
            returnEntity=tList.get(0);
        }
        return returnEntity;
    }
    protected Query createCriteriaQuery(String by,Object object){
        CriteriaBuilder cb=getEntityManager().getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = cb.createQuery(entityClass);
        Metamodel m =  getEntityManager().getMetamodel();
        EntityType<T> entity = m.entity(entityClass);
        Root<T> entityRoot = criteriaQuery.from(entityClass);
        Selection<T> selection = criteriaQuery.where(cb.equal(entityRoot.get(entity.getAttribute(by).getName()),object)).getSelection();
        criteriaQuery.where(cb.equal(entityRoot.get(entity.getAttribute(by).getName()),object)).select(selection);
        return getEntityManager().createQuery(criteriaQuery);
    }


}
