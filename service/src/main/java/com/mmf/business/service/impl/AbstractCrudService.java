package com.mmf.business.service.impl;

import com.mmf.business.domain.DomainClass;
import com.mmf.business.service.BusinessServiceException;
import com.mmf.business.service.CrudService;
import com.mmf.db.dao.DataAccessException;
import com.mmf.db.dao.GenericDao;
import com.mmf.db.model.EntityClass;
import com.mmf.db.util.ValidationUtils;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author svetlana.voyteh
 * @date: 5/12/12
 */
public abstract class AbstractCrudService<Identifier extends Serializable, EntityObject extends EntityClass<Identifier>, ManagerDao extends GenericDao<Identifier, EntityObject>>
        implements CrudService<Identifier, EntityObject> {

    /**
     * Returns instance of service dao
     *
     * @return service main DAO instance
     */
    protected abstract ManagerDao getDao();

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityObject instance() throws BusinessServiceException {
        try {
            EntityObject entity = getDao().transientInstance();
            return entity;
        } catch (DataAccessException ex) {
            throw new BusinessServiceException(ex);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(rollbackFor = BusinessServiceException.class)
    public List<EntityObject> list() throws BusinessServiceException {
        try {
            return getDao().list();
        } catch (DataAccessException dae) {
            throw new BusinessServiceException(dae);
        }
    }



    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(rollbackFor = BusinessServiceException.class)
    public void create(EntityObject entity) throws BusinessServiceException {
        try {
            ValidationUtils.isNullObject(entity, "bl.create.null-object", BusinessServiceException.class);
            getDao().create(entity);
        } catch (DataAccessException dae) {
            throw new BusinessServiceException(dae);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(rollbackFor = BusinessServiceException.class)
    public EntityObject get(Identifier id) throws BusinessServiceException {
        try {
            ValidationUtils.isNullObject(id, "bl.get.null-id", BusinessServiceException.class);
            return getDao().read(id);
        } catch (DataAccessException dae) {
            throw new BusinessServiceException(dae);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(rollbackFor = BusinessServiceException.class)
    public void update(EntityObject entity) throws BusinessServiceException {
        try {
            ValidationUtils.isNullObject(entity, "bl.update.object-not-found", BusinessServiceException.class);
            getDao().update(entity);
        } catch (DataAccessException dae) {
            throw new BusinessServiceException(dae);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(rollbackFor = BusinessServiceException.class)
    public void delete(Identifier id) throws BusinessServiceException {
        try {
            ValidationUtils.isNullObject(id, "bl.delete.null-object", BusinessServiceException.class);
            EntityObject entity = getDao().read(id);
            ValidationUtils.isNullObject(entity, "bl.delete.object-not-found", BusinessServiceException.class);
            getDao().delete(entity);
        } catch (DataAccessException dae) {
            throw new BusinessServiceException(dae);
        }
    }

    /**
     * Converts domain object to entity object
     *
     * @param domain
     * @param entity
     * @throws BusinessServiceException
     */
//    public abstract void convertToEntity(DomainObject domain, EntityObject entity) throws BusinessServiceException;
//
//    /**
//     * Converts entity object to domain object
//     *
//     * @param entity
//     * @return
//     * @throws BusinessServiceException
//     */
//    public abstract DomainObject convertToDomain(EntityObject entity) throws BusinessServiceException;
//
//    protected List<DomainObject> convertToDomain(Collection<EntityObject> entities) throws BusinessServiceException {
//        List<DomainObject> domains = new ArrayList<DomainObject>(entities.size());
//        for (EntityObject entityobject : entities) {
//            domains.add(convertToDomain(entityobject));
//        }
//        return domains;
//    }
}
