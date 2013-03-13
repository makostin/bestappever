package com.mmf.business.impl;

import com.mmf.business.BusinessServiceException;
import com.mmf.business.GroupService;
import com.mmf.business.domain.Group;
import com.mmf.business.domain.Specialty;
import com.mmf.business.domain.SpecialtyInfo;
import com.mmf.business.domain.Student;
import com.mmf.business.domain.utils.GroupHelper;
import com.mmf.business.domain.utils.SpecialtyHelper;
import com.mmf.business.domain.utils.StudentHelper;
import com.mmf.db.dao.DataAccessException;
import com.mmf.db.dao.GroupDao;
import com.mmf.db.dao.SpecialtyDao;
import com.mmf.db.model.GroupEntity;
import com.mmf.db.model.SpecialtyEntity;
import com.mmf.db.model.StudentEntity;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * svetlana.voyteh
 * 12.03.13
 */
@Named
public class GroupServiceImpl extends AbstractCrudService<Long, Group, GroupEntity, GroupDao> implements GroupService{

    @Inject
    private GroupDao groupDao;

    @Inject
    private SpecialtyDao specialtyDao;

    @Override
    protected GroupDao getDao() {
        return groupDao;
    }

    @Override
    public void convertToEntity(Group domain, GroupEntity entity) throws BusinessServiceException {
        if (domain != null){
            try {
                Specialty specialty = domain.getSpecialty();
                if(specialty != null){
                    SpecialtyEntity specialtyEntity = specialtyDao.getEntityInstance(specialty.getId());
                    SpecialtyHelper.convertToEntity(specialty, specialtyEntity);

                    if (entity != null){
                        entity.setSpecialty(specialtyEntity);
                    }
                }

                Group mainGroup = domain.getMainGroup();
                if (mainGroup != null){
                    GroupEntity groupEntity = groupDao.getEntityInstance(mainGroup.getId());
                    GroupHelper.convertToEntity(mainGroup, groupEntity);

                    if(entity != null){
                        entity.setMainGroup(groupEntity);
                    }

                }
            } catch (DataAccessException e) {
                throw new BusinessServiceException("Conversion to group entity error.", e);
            }
            GroupHelper.convertToEntity(domain, entity);
        }
    }

    @Override
    public Group convertToDomain(GroupEntity entity) throws BusinessServiceException {
        if (entity == null) {
            return null;
        }

        Group group = GroupHelper.convertToDomain(entity);
        group.setSpecialty(SpecialtyHelper.convertToDomain(entity.getSpecialty()));
        group.setMainGroup(GroupHelper.convertToDomain(entity.getMainGroup()));
        for (StudentEntity studentEntity : entity.getStudents()){
            group.getStudents().add(StudentHelper.convertToDomain(studentEntity));
        }
        return group;
    }

    @Override
    @Transactional(rollbackFor = BusinessServiceException.class)
    public List<SpecialtyInfo> getSpecialtyInfos() {
        List<SpecialtyInfo> specialtyInfos = new ArrayList<SpecialtyInfo>();
        List<GroupEntity> mainGroups = groupDao.getMainGroups();
        Map<String, Integer> map = new HashMap<String, Integer>();
        for(GroupEntity entity : mainGroups){
            String specialtyName = entity.getSpecialty().getName();
            if (map.containsKey(specialtyName)){
                specialtyInfos.get(map.get(specialtyName)).getGroupNumbers().add(entity.getName());
            } else {
                SpecialtyInfo info = new SpecialtyInfo();
                info.setId(entity.getSpecialty().getId());
                info.setName(specialtyName);
                info.getGroupNumbers().add(entity.getName());
                specialtyInfos.add(info);
                map.put(specialtyName, specialtyInfos.size()-1);
            }
        }
        return specialtyInfos;
    }
}
