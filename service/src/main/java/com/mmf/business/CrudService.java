package com.mmf.business;

import com.mmf.business.domain.DomainClass;

import java.io.Serializable;
import java.util.List;

/**
 * This interface is a common interface for services which describes CRUD
 * actions performed with the entity.
 * 
 * svetlana.voyteh
 * 05.03.13
 * 
 * @param <Identifier>
 *            used entity identifier
 * @param <DomainObject>
 *            used domain class
 */
public interface CrudService<Identifier extends Serializable, DomainObject extends DomainClass<Identifier>> {

    /**
     * Creates empty object.
     * 
     * @return DomainObject
     * @throws BusinessServiceException
     *             if error occurs
     */
    DomainObject instance() throws BusinessServiceException;

    /**
     * Read and return object.
     * 
     * @param id
     *            object id
     * @return DomainObject
     * @throws BusinessServiceException
     *             if error occurs
     */
    DomainObject get(Identifier id) throws BusinessServiceException;

    /**
     * Create object.
     * 
     * @param businessObject
     *            object to create
     * @throws BusinessServiceException
     *             if error occurs
     */
    void create(DomainObject businessObject) throws BusinessServiceException;

    /**
     * Update existing object.
     * 
     * @param businessObject
     *            object to update
     * @throws BusinessServiceException
     *             if error occurs
     */
    void update(DomainObject businessObject) throws BusinessServiceException;

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
    List<DomainObject> list() throws BusinessServiceException;
}