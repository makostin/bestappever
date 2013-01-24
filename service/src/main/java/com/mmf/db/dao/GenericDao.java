package com.mmf.db.dao;

import com.mmf.db.model.EntityClass;

import java.io.Serializable;
import java.util.List;
import java.util.Properties;

/**
 * @author svetlana.voyteh
 * @date: 5/12/12
 */
public interface GenericDao<Identifier extends Serializable, Entity extends EntityClass<Identifier>> {


    /**
     * Creates new instance of transient entity object.
     *
     * @return Entity object.
     * @throws DataAccessException
     */
    Entity transientInstance() throws DataAccessException;

    /**
     * Persists transient entity object into persistent storage.
     *
     * @param entity Entity object.
     * @throws DataAccessException
     */
    void create(Entity entity) throws DataAccessException;

    /**
     * Reads data from persistent storage.
     *
     * @param id Identifier of the object to load.
     * @return Entity object.
     * @throws DataAccessException
     */
    Entity read(Identifier id) throws DataAccessException;

    /**
     * Saves persistent object changes into persistent storage.
     *
     * @param entity Entity object.
     * @throws DataAccessException
     */
    void update(Entity entity) throws DataAccessException;

    /**
     * Removes persistent object from persistent storage.
     *
     * @param entity Entity object.
     * @throws DataAccessException
     */
    void delete(Entity entity) throws DataAccessException;

    /**
     * Reads full list of entity objects from persistent storage.
     *
     * @return List of entity objects.
     * @throws DataAccessException
     */
    List<Entity> list() throws DataAccessException;

    /**
     * Creates new instance of transient entity object or gets it from database.
     *
     * @param id Identifier of the object to load.
     * @return Entity object.
     * @throws DataAccessException
     */
    Entity getEntityInstance(Identifier id) throws DataAccessException;
}
