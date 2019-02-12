package com.samsolutions.logistics.mainlogistics.dao;

import com.samsolutions.logistics.mainlogistics.entities.Contacts;
import com.samsolutions.logistics.mainlogistics.services.utils.PaginationDao;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ContactsDaoImpl extends AbstractJpaDao<Contacts,Long>{

    @PersistenceContext
    private EntityManager entityManager;
    @Inject
    private PaginationDao<Contacts,Long> paginationDao;

    @PostConstruct
    public void init(){
        setEntityClass(Contacts.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    public Contacts findByEmail(String email){
        return findBy("email",email);
    }

    public List findByEmailPageable(String email, int page, int elements){
        paginationDao.setEntityManager(entityManager);
        paginationDao.setEntityClass(Contacts.class);
        return paginationDao.getPage(page,elements,createCriteriaQuery("email",email));
    }

}
