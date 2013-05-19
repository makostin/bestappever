package com.mmf.db.dao.jpa;

import com.mmf.db.dao.DataAccessException;
import com.mmf.db.dao.StudentDao;
import com.mmf.db.model.GroupEntity;
import com.mmf.db.model.StudentEntity;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import java.util.List;

/**
 * User: svetlana.voyteh
 * Date: 12.05.13
 */
@Named
public class StudentDaoImpl extends GenericJpaDao<Long, StudentEntity> implements StudentDao{
    @Override
    protected Class<StudentEntity> getEntityClass() {
        return StudentEntity.class;
    }

    @Override
    public List<StudentEntity> getGroupStudents(Long groupId) {
        EntityManager entityManager = getEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<StudentEntity> criteriaQuery = criteriaBuilder.createQuery(StudentEntity.class);
        Root<StudentEntity> root = criteriaQuery.from(StudentEntity.class);
        CriteriaQuery<StudentEntity> select = criteriaQuery.select(root);

        Subquery<GroupEntity> groupSubQuery = criteriaQuery.subquery(GroupEntity.class);
        Root<GroupEntity> rootGroup = groupSubQuery.from(GroupEntity.class);
        groupSubQuery.select(rootGroup);

        groupSubQuery.
                where(criteriaBuilder.or
                        (
                                criteriaBuilder.equal(rootGroup.get("id"), groupId),
                                criteriaBuilder.equal(rootGroup.get("mainGroup").get("id"), groupId)
                        )
                );

        select.where(criteriaBuilder.in(root.get("group")).value(groupSubQuery));
        TypedQuery<StudentEntity> query = entityManager.createQuery(select);
        return query.getResultList();
    }

//    @Override
//    public void create(StudentEntity studentEntity) throws DataAccessException {
//        try {
//            if (studentEntity == null){
//                throw new NullPointerException("create: Entity is null.");
//            }
//            getEntityManager().merge(studentEntity);
//        } catch (RuntimeException ex) {
//            throw new DataAccessException(ex);
//        }
//    }
}
