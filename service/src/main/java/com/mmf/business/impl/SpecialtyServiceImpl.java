package com.mmf.business.impl;

import com.mmf.business.BusinessServiceException;
import com.mmf.business.SpecialtyService;
import com.mmf.business.domain.Specialty;
import com.mmf.business.domain.SpecialtyInfo;
import com.mmf.business.domain.utils.GroupHelper;
import com.mmf.business.domain.utils.SpecialtyHelper;
import com.mmf.db.dao.DataAccessException;
import com.mmf.db.dao.SpecialtyDao;
import com.mmf.db.model.GroupEntity;
import com.mmf.db.model.SpecialtyEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * svetlana.voyteh
 * 05.03.13
 */
@Named
public class SpecialtyServiceImpl extends AbstractCrudService<Long, Specialty, SpecialtyEntity, SpecialtyDao> implements SpecialtyService{

    @Autowired
    private SpecialtyDao specialtyDao;

    @Override
    protected SpecialtyDao getDao() {
        return specialtyDao;
    }

    @Override
    public void convertToEntity(Specialty domain, SpecialtyEntity entity) throws BusinessServiceException {
        if (domain != null){
            SpecialtyHelper.convertToEntity(domain, entity);
        }
    }

    @Override
    public Specialty convertToDomain(SpecialtyEntity entity) throws BusinessServiceException {
        if (entity == null) {
            return null;
        }

        Specialty specialty = SpecialtyHelper.convertToDomain(entity);
        for (GroupEntity groupEntity : entity.getGroups()){
            specialty.getGroups().add(GroupHelper.convertToDomain(groupEntity));
        }
        return specialty;
    }

    @Override
    @Transactional(rollbackFor = BusinessServiceException.class)
    public List<SpecialtyInfo> getSpecialtyInfos() throws BusinessServiceException {
        List<SpecialtyEntity> specialtyList = specialtyDao.getSpecialtiesWithGroups();
        List<SpecialtyInfo> specialtyInfoList = new ArrayList<SpecialtyInfo>();
        for(SpecialtyEntity entity : specialtyList){
            SpecialtyInfo info = new SpecialtyInfo();
            info.setId(entity.getId());
            info.setName(entity.getName());

            List<String> groupNumbers = new ArrayList<String>();
            for (GroupEntity groupEntity : entity.getGroups()){
                if(groupEntity.getMainGroup() == null){
                    groupNumbers.add(groupEntity.getName());
                }
            }
            info.setGroupNumbers(groupNumbers);

            specialtyInfoList.add(info);
        }
        return specialtyInfoList;
    }
}
