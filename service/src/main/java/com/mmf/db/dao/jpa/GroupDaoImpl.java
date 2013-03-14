package com.mmf.db.dao.jpa;

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
}
