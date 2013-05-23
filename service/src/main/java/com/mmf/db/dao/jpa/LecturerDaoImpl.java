package com.mmf.db.dao.jpa;

import com.mmf.db.dao.LecturerDao;
import com.mmf.db.model.LecturerEntity;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * svetlana.voyteh
 * 05.03.13
 */

@Named
public class LecturerDaoImpl extends GenericJpaDao<Long, LecturerEntity> implements LecturerDao {
    @Override
    protected Class<LecturerEntity> getEntityClass() {
        return LecturerEntity.class;
    }

    @Override
    public LecturerEntity getLecturer(String login) {
        EntityManager entityManager = getEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<LecturerEntity> criteriaQuery = criteriaBuilder.createQuery(LecturerEntity.class);
        Root<LecturerEntity> root = criteriaQuery.from(LecturerEntity.class);
        criteriaQuery.where(criteriaBuilder.equal(root.get("login"), login));
        TypedQuery<LecturerEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getSingleResult();
    }
}
