package com.mmf.business.impl;

import com.mmf.business.BusinessServiceException;
import com.mmf.business.GroupService;
import com.mmf.business.domain.Group;
import com.mmf.business.domain.utils.GroupHelper;
import com.mmf.business.domain.utils.SpecialtyHelper;
import com.mmf.business.domain.utils.StudentHelper;
import com.mmf.db.dao.DataAccessException;
import com.mmf.db.dao.GroupDao;
import com.mmf.db.dao.SpecialtyDao;
import com.mmf.db.dao.StudentDao;
import com.mmf.db.model.GroupEntity;
import com.mmf.db.model.SpecialtyEntity;
import com.mmf.db.model.StudentEntity;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * svetlana.voyteh
 * 12.03.13
 */
@Named
public class GroupServiceImpl extends AbstractCrudService<Long, Group, GroupEntity, GroupDao> implements GroupService {

    @Inject
    private GroupDao groupDao;

    @Inject
    private SpecialtyDao specialtyDao;

    @Inject
    private StudentDao studentDao;

    @Override
    protected GroupDao getDao() {
        return groupDao;
    }

    @Override
    public void convertToEntity(Group domain, GroupEntity entity) throws BusinessServiceException {
        if (domain != null) {
            try {
                SpecialtyEntity specialtyEntity = specialtyDao.getEntityInstance(domain.getSpecialtyId());
                if (specialtyEntity == null) {
                    throw new BusinessServiceException("Such specialty doesn't exist.");
                }

                if (entity != null) {
                    entity.setSpecialty(specialtyEntity);
                }

                if (domain.getSubgroup() == null) {
                    if (entity != null) {
                        entity.setMainGroup(null);
                    }
                } else {
                    GroupEntity mainGroup = groupDao.getMainGroup(domain.getNumber());
                    if (mainGroup == null) {
                        throw new BusinessServiceException("Such group doesn't exist.");
                    }

                    if (entity != null) {
                        entity.setMainGroup(mainGroup);
                    }

                }
                GroupHelper.convertToEntity(domain, entity);
            } catch (DataAccessException e) {
                throw new BusinessServiceException("Conversion to group entity error.", e);
            }
        }
    }

    @Override
    public Group convertToDomain(GroupEntity entity) throws BusinessServiceException {
        if (entity == null) {
            return null;
        }

        Group group = GroupHelper.convertToDomain(entity);
        group.setSpecialty(SpecialtyHelper.convertToDomain(entity.getSpecialty()));
        for (StudentEntity studentEntity : studentDao.getGroupStudents(group.getId())) {
            group.getStudents().add(StudentHelper.convertToDomain(studentEntity));
        }
        return group;
    }

}
