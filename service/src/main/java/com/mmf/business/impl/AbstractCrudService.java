package com.mmf.business.impl;

import com.mmf.business.BusinessServiceException;
import com.mmf.business.CrudService;
import com.mmf.business.domain.DomainClass;
import com.mmf.db.dao.DataAccessException;
import com.mmf.db.dao.GenericDao;
import com.mmf.db.model.EntityClass;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Class represents default implementation of the CrudService interface.
 * 
 *  svetlana.voyteh
 * 05.03.13
 * 
 */
public abstract class AbstractCrudService<Identifier extends Serializable, DomainObject extends DomainClass<Identifier>, EntityObject extends EntityClass<Identifier>, ManagerDao extends GenericDao<Identifier, EntityObject>>
        implements CrudService<Identifier, DomainObject> {

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
    public DomainObject instance() throws BusinessServiceException {
        try {
            EntityObject entity = getDao().transientInstance();
            return convertToDomain(entity);
        } catch (DataAccessException ex) {
            throw new BusinessServiceException(ex);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(rollbackFor = BusinessServiceException.class)
    public void create(DomainObject domain) throws BusinessServiceException {
        try {
            if (domain == null){
                throw new DataAccessException("create: Domain object is null.");
            }
            EntityObject entity = getDao().transientInstance();
            convertToEntity(domain, entity);
            getDao().create(entity);
            domain.setId(entity.getId());
        } catch (DataAccessException dae) {
            throw new BusinessServiceException(dae);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(rollbackFor = BusinessServiceException.class)
    public DomainObject get(Identifier id) throws BusinessServiceException {
        try {
            if (id == null){
                throw new DataAccessException("get: Domain id is null.");
            }
            return convertToDomain(getDao().read(id));
        } catch (DataAccessException dae) {
            throw new BusinessServiceException(dae);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(rollbackFor = BusinessServiceException.class)
    public void update(DomainObject domain) throws BusinessServiceException {
        try {
            if (domain == null){
                throw new DataAccessException("update: Domain object is null.");
            }
            EntityObject entity = getDao().read(domain.getId());
            if (entity == null){
                throw new DataAccessException("update: Entity object is null.");
            }
            convertToEntity(domain, entity);
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
            if (id == null){
                throw new DataAccessException("delete: Domain id is null.");
            }
            EntityObject entity = getDao().read(id);
            if (entity == null){
                throw new DataAccessException("delete: Entity object is null.");
            }
            getDao().delete(entity);
        } catch (DataAccessException dae) {
            throw new BusinessServiceException(dae);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(rollbackFor = BusinessServiceException.class)
    public List<DomainObject> list() throws BusinessServiceException {
        try {
            return convertToDomain(getDao().list());
        } catch (DataAccessException dae) {
            throw new BusinessServiceException(dae);
        }
    }

    protected List<DomainObject> convertToDomain(Collection<EntityObject> entities) throws BusinessServiceException {
        List<DomainObject> domains = new ArrayList<DomainObject>(entities.size());
        for (EntityObject entityobject : entities) {
            domains.add(convertToDomain(entityobject));
        }
        return domains;
    }

    /**
     * Converts domain object to entity object
     * 
     * @param domain
     * @param entity
     * @throws BusinessServiceException 
     */
    public abstract void convertToEntity(DomainObject domain, EntityObject entity) throws BusinessServiceException;

    /**
     * Converts entity object to domain object
     * 
     * @param entity
     * @return
     * @throws BusinessServiceException 
     */
    public abstract DomainObject convertToDomain(EntityObject entity) throws BusinessServiceException;
}