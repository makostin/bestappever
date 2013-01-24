package com.mmf.db.dao.jpa;

import com.mmf.db.dao.DataAccessException;
import com.mmf.db.dao.GenericDao;
import com.mmf.db.model.EntityClass;
import com.mmf.db.util.ValidationUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;
import java.util.Properties;

/**
 * @author svetlana.voyteh
 * @date: 5/12/12
 */
public abstract class GenericJpaDao <Identifier extends Serializable, Entity extends EntityClass<Identifier>> implements GenericDao<Identifier, Entity> {


    public static final String URL = "jdbc:mysql://localhost:3306/";
    public static final String DB_NAME = "schedule";
    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "admin";
    public static final String CHARACTER_ENCODING = "UTF-8";

    public Properties getProperties(){
        Properties properties=new Properties();
        properties.setProperty("user",USERNAME);
        properties.setProperty("password",PASSWORD);
        properties.setProperty("useUnicode","true");
        properties.setProperty("characterEncoding",CHARACTER_ENCODING);
        return properties;
    }

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
        ValidationUtils.isNullObject(entity, "dal.create.null-entity", DataAccessException.class);
        ValidationUtils.isNotNullObject(entity.getId(), "dal.create.not-null-id", DataAccessException.class);

        try {
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
        ValidationUtils.isNullObject(entity, "dal.update.null-entity", DataAccessException.class);
        ValidationUtils.isNullObject(entity.getId(), "dal.update.null-id", DataAccessException.class);

        try {
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
        ValidationUtils.isNullObject(entity, "dal.delete.null-entity", DataAccessException.class);
        ValidationUtils.isNullObject(entity.getId(), "dal.delete.null-id", DataAccessException.class);

        try {
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
        ValidationUtils.isNullObject(id, "dal.read.id-required", DataAccessException.class);

        try {
            return getEntityManager().find(getEntityClass(), id);
        } catch (RuntimeException ex) {
            throw new DataAccessException(ex);
        }
    }

    /**
     * {@inheritDoc}
     */
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
