package com.samsolutions.logistics.mainlogistics.dao;

import com.samsolutions.logistics.mainlogistics.entities.Firms;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class FirmsDaoImpl implements DaoInterface<Firms,Long> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public void persist(Firms entity) {

    }

    @Override
    public void update(Firms entity) {

    }

    @Override
    public Firms findById(Long aLong) {
        return null;
    }

    @Override
    public void delete(Firms entity) {

    }

    @Override
    public List<Firms> findAll() {
        return null;
    }
}
