package com.jpa.hibernate.demodao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

@Transactional
public abstract class AbstractJpaDAO<Entity>{

    private Class<Entity> entityClass;

    @PersistenceContext
    EntityManager entityManager;

    public final void setEntityClass( Class< Entity > entityClass ){
        this.entityClass = entityClass;
    }

    public Entity findOne( Long id ){
        return entityManager.find( entityClass, id );
    }
    public List< Entity > findAll(){
        return entityManager.createQuery( "from " + entityClass.getName() )
                .getResultList();
    }

    public void create( Entity entity ){
        entityManager.persist( entity );
    }

    public Entity update( Entity entity ){
        return entityManager.merge( entity );
    }

    public void delete( Entity entity ){
        entityManager.remove( entity );
    }
    public void deleteById( long entityId ){
        Entity entity = findOne( entityId );
        delete( entity );
    }
}