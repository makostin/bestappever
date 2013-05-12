package com.mmf.db.dao.jpa;

import com.mmf.business.domain.User;
import com.mmf.db.dao.UserDao;
import com.mmf.db.model.GroupEntity;
import com.mmf.db.model.SpecialtyEntity;
import com.mmf.db.model.UserEntity;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

/**
 * svetlana.voyteh
 * 05.03.13
 */
@Named
public class UserDaoImpl extends GenericJpaDao<Long, UserEntity> implements UserDao{


    @Override
    protected Class<UserEntity> getEntityClass() {
        return UserEntity.class;
    }

    @Override
    public UserEntity getUser(String login) {
        EntityManager entityManager = getEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserEntity> criteriaQuery = criteriaBuilder.createQuery(UserEntity.class);
        Root<UserEntity> root = criteriaQuery.from(UserEntity.class);
        criteriaQuery.where(criteriaBuilder.equal(root.get("login"), login));
        TypedQuery<UserEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getSingleResult();
    }
}
