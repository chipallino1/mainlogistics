package com.samsolutions.logistics.mainlogistics.dao;

import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

@Component
public interface DaoInterface<T, Id extends Serializable> {

    EntityManager getEntityManager();

    void persist(T entity);

    void update(T entity);

    T findById(Id id);

    void delete(T entity);

    List<T> findAll();

}