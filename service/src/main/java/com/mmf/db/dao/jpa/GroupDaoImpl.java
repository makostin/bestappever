package com.mmf.db.dao.jpa;

import com.mmf.db.dao.DataAccessException;
import com.mmf.db.dao.GroupDao;
import com.mmf.db.model.GroupEntity;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * svetlana.voyteh
 * 12.03.13
 */
@Named
public class GroupDaoImpl extends GenericJpaDao<Long, GroupEntity> implements GroupDao{


    @Override
    protected Class<GroupEntity> getEntityClass() {
        return GroupEntity.class;
    }

    @Override
    public List<GroupEntity> getMainGroups() {
        EntityManager entityManager = getEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<GroupEntity> criteriaQuery = criteriaBuilder.createQuery(GroupEntity.class);
        Root<GroupEntity> root = criteriaQuery.from(GroupEntity.class);
        criteriaQuery.where(root.get("mainGroup").isNull());
        TypedQuery<GroupEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public GroupEntity getMainGroup(Integer number) {
        EntityManager entityManager = getEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<GroupEntity> criteriaQuery = criteriaBuilder.createQuery(GroupEntity.class);
        Root<GroupEntity> root = criteriaQuery.from(GroupEntity.class);
        criteriaQuery.where(criteriaBuilder.like(root.<String>get("name"), String.valueOf(number)));
        TypedQuery<GroupEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getSingleResult();
    }

    @Override
    public void delete(GroupEntity entity) throws DataAccessException {
        try {
            if (entity == null || entity.getId() == null){
                throw new NullPointerException("delete: Entity or entityId is null.");
            }
            if(entity.getMainGroup() == null){
                deleteSubgroups(entity.getId());
            }
            getEntityManager().remove(entity);
        } catch (RuntimeException ex) {
            throw new DataAccessException(ex);
        }
    }

    private void deleteSubgroups(Long id) {
        EntityManager entityManager = getEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<GroupEntity> criteriaQuery = criteriaBuilder.createQuery(GroupEntity.class);
        Root<GroupEntity> root = criteriaQuery.from(GroupEntity.class);
        criteriaQuery.where(criteriaBuilder.equal(root.get("mainGroup").get("id"), id));
        TypedQuery<GroupEntity> query = entityManager.createQuery(criteriaQuery);
        
        List<GroupEntity> subgroups = query.getResultList();
        for (GroupEntity groupEntity : subgroups){
            entityManager.remove(groupEntity);
        }
    }
}
