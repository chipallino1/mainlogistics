package com.jpa.hibernate.demodao;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

public abstract class AbstractJpaDAO< T extends Serializable> {

    private Class< T > entityClass;

    @PersistenceContext
    EntityManager entityManager;

    public final void setEntityClass( Class< T > entityClass ){
        this.entityClass = entityClass;
    }

    public T findOne( Long id ){
        return entityManager.find( entityClass, id );
    }
    public List< T > findAll(){
        return entityManager.createQuery( "from " + entityClass.getName() )
                .getResultList();
    }

    public void create( T entity ){
        entityManager.persist( entity );
    }

    public T update( T entity ){
        return entityManager.merge( entity );
    }

    public void delete( T entity ){
        entityManager.remove( entity );
    }
    public void deleteById( long entityId ){
        T entity = findOne( entityId );
        delete( entity );
    }
}