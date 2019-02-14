package com.samsolutions.logistics.mainlogistics.dao;

import com.samsolutions.logistics.mainlogistics.entities.Contacts;
import com.samsolutions.logistics.mainlogistics.services.utils.PaginationDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    private PaginationDao paginationDao;

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

    public List findByEmailLikePageable(String email, int page, int elements){
        return null;
    }

}
