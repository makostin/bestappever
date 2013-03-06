package com.mmf.db.dao.jpa;


import com.mmf.db.dao.DataAccessException;
import com.mmf.db.dao.GenericDao;
import com.mmf.db.model.EntityClass;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

/**
 * JPA implementation of CRUD data access object.
 *
 * @param <Identifier> Type of entity identifier.
 * @param <Entity> Entity class.
 * 
 * @author svetlana.voyteh
 * @date: 5/11/12
 */
public abstract class GenericJpaDao<Identifier extends Serializable, Entity extends EntityClass<Identifier>> implements GenericDao<Identifier, Entity> {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * @{@inheritDoc}
     */
    @Override
    public Entity transientInstance() throws DataAccessException {
        try {
            return getEntityClass().newInstance();
        } catch (InstantiationException ex) {
            throw new DataAccessException(ex);
        } catch (IllegalAccessException ex) {
            throw new DataAccessException(ex);
        }
    }

    /**
     * @{@inheritDoc}
     */
    @Override
    public void create(Entity entity) throws DataAccessException {
        try {
            if (entity == null){
                throw new NullPointerException("create: Entity is null.");
            }
            getEntityManager().persist(entity);
        } catch (RuntimeException ex) {
            throw new DataAccessException(ex);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Entity entity) throws DataAccessException {
        try {
            if (entity == null || entity.getId() == null){
                throw new NullPointerException("update: Entity or entityId is null.");
            }
            getEntityManager().persist(entity);
        } catch (RuntimeException ex) {
            throw new DataAccessException(ex);
        }
    }

    /**
     * @{@inheritDoc}
     */
    @Override
    public void delete(Entity entity) throws DataAccessException {
        try {
            if (entity == null || entity.getId() == null){
                throw new NullPointerException("delete: Entity or entityId is null.");
            }
            getEntityManager().remove(entity);
        } catch (RuntimeException ex) {
            throw new DataAccessException(ex);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity read(Identifier id) throws DataAccessException {
        try {
            if (id == null){
                throw new NullPointerException("read: EntityId is null.");
            }
            return getEntityManager().find(getEntityClass(), id);
        } catch (RuntimeException ex) {
            throw new DataAccessException(ex);
        }
    }

    @Override
    public List<Entity> list() throws DataAccessException {
        CriteriaBuilder criteriaBuilder = getCriteriaBuilder();

        CriteriaQuery<Entity> criteriaQuery = criteriaBuilder.createQuery(getEntityClass());
        Root<Entity> from = criteriaQuery.from(getEntityClass());
        CriteriaQuery<Entity> select = criteriaQuery.select(from);

        TypedQuery<Entity> typedQuery = getEntityManager().createQuery(select);

        return typedQuery.getResultList();
    }

    /**
     * Returns instance of JPA 2 criteria builder.
     *
     * @return Criteria builder.
     */
    protected CriteriaBuilder getCriteriaBuilder() {
        return getEntityManager().getCriteriaBuilder();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity getEntityInstance(Identifier id) throws DataAccessException {
        if (id != null) {
            return read(id);
        } else {
            return transientInstance();
        }
    }

    /**
     * Returns EntityManager instance.
     * 
     * @return EntityManager
     */
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     * Returns entity class.
     * 
     * @return Class<EntityObject>
     */
    protected abstract Class<Entity> getEntityClass();
}