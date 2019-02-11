package com.samsolutions.logistics.mainlogistics.dao;

import com.samsolutions.logistics.mainlogistics.entities.Contacts;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class ContactsDaoImpl extends AbstractJpaDao<Contacts,Long>{

    @PersistenceContext
    private EntityManager entityManager;

    @PostConstruct
    public void init(){
        setEntityClass(Contacts.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
