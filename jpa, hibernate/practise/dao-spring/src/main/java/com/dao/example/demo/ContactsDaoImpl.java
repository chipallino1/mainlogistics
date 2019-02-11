package com.dao.example.demo;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ContactsDaoImpl implements DaoInterface<Book,Long>{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void persist(Book entity) {

    }

    @Override
    public void update(Book entity) {

    }

    @Override
    public Book findById(Long aLong) {
        return null;
    }

    @Override
    public void delete(Book entity) {

    }

    @Override
    public List<Book> findAll() {
        return null;
    }

    @Override
    public void deleteAll() {

    }
}
