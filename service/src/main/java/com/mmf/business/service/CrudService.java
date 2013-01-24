package com.mmf.business.service;

import com.mmf.db.model.EntityClass;

import java.io.Serializable;
import java.util.List;

/**
 * @author svetlana.voyteh
 * @date: 5/12/12
 */
public interface CrudService<Identifier extends Serializable, EntityObject extends EntityClass<Identifier>> {

    /**
     * Creates empty object.
     *
     * @return DomainObject
     * @throws BusinessServiceException
     *             if error occurs
     */
    EntityObject instance() throws BusinessServiceException;

    /**
     * Read and return object.
     *
     * @param id
     *            object id
     * @return DomainObject
     * @throws BusinessServiceException
     *             if error occurs
     */
    EntityObject get(Identifier id) throws BusinessServiceException;

    /**
     * Create object.
     *
     * @param businessObject
     *            object to create
     * @throws BusinessServiceException
     *             if error occurs
     */
    void create(EntityObject businessObject) throws BusinessServiceException;

    /**
     * Update existing object.
     *
     * @param businessObject
     *            object to update
     * @throws BusinessServiceException
     *             if error occurs
     */
    void update(EntityObject businessObject) throws BusinessServiceException;

    /**
     * Delete object.
     *
     * @param id
     *          id of the object to delete
     * @throws BusinessServiceException
     *             if error occurs
     */
    void delete(Identifier id) throws BusinessServiceException;

    /**
     * Read add returns list.
     *
     * @return list of objects
     * @throws BusinessServiceException
     *             if error occurs
     */
    List<EntityObject> list() throws BusinessServiceException;
}
