package com.mmf.db.dao.jpa;

import com.mmf.db.dao.SpecialtyDao;
import com.mmf.db.model.GroupEntity;
import com.mmf.db.model.SpecialtyEntity;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * svetlana.voyteh
 * 05.03.13
 */
@Named
public class SpecialtyDaoImpl extends GenericJpaDao<Long, SpecialtyEntity> implements SpecialtyDao {

    @Override
    protected Class<SpecialtyEntity> getEntityClass() {
        return SpecialtyEntity.class;
    }

    @Override
    public List<SpecialtyEntity> getSpecialtiesWithGroups() {
        EntityManager entityManager = getEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<SpecialtyEntity> criteriaQuery = criteriaBuilder.createQuery(SpecialtyEntity.class);
        Root<SpecialtyEntity> root = criteriaQuery.from(SpecialtyEntity.class);
        Join<SpecialtyEntity, GroupEntity> join = root.join("groups");
        criteriaQuery.where(join.get("mainGroup").isNull()).groupBy(root.get("id"));
        TypedQuery<SpecialtyEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }
}
