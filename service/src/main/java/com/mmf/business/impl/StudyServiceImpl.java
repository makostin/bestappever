package com.mmf.business.impl;

import com.mmf.business.BusinessServiceException;
import com.mmf.business.StudyService;
import com.mmf.business.domain.Specialty;
import com.mmf.business.domain.SpecialtyInfo;
import com.mmf.business.domain.Study;
import com.mmf.business.domain.utils.*;
import com.mmf.db.dao.*;
import com.mmf.db.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

/**
 * svetlana.voyteh
 * 05.03.13
 */
@Named
public class StudyServiceImpl extends AbstractCrudService<Long, Study, StudyEntity, StudyDao> implements StudyService{

    @Autowired
    private StudyDao studyService;

    @Autowired
    private GroupDao groupDao;

    @Autowired
    private LecturerDao lecturerDao;

    @Autowired
    private CurriculumDao curriculumDao;

    @Override
    protected StudyDao getDao() {
        return studyService;
    }

    @Override
    public void convertToEntity(Study domain, StudyEntity entity) throws BusinessServiceException {
        if (domain != null && entity != null){
            try {
                GroupEntity groupEntity = groupDao.getEntityInstance(domain.getGroupId());
                if (groupEntity == null){
                    throw new BusinessServiceException("Such group doesn't exist.");
                }
                entity.setGroup(groupEntity);

                LecturerEntity lecturerEntity = lecturerDao.getEntityInstance(domain.getLecturerId());
                if(lecturerEntity == null){
                    throw new BusinessServiceException("Such lecturer doesn't exist.");
                }
                entity.setLecturer(lecturerEntity);

                CurriculumEntity curriculumEntity = curriculumDao.getEntityInstance(domain.getCurriculumId());
                if(curriculumEntity == null){
                    throw new BusinessServiceException("Such curriculum doesn't exist.");
                }
                entity.setCurriculum(curriculumEntity);

            } catch (DataAccessException e) {
                throw new BusinessServiceException("Conversion to study entity error.", e);
            }
        }
    }

    @Override
    public Study convertToDomain(StudyEntity entity) throws BusinessServiceException {
        if (entity == null) {
            return null;
        }

        Study study = StudyHelper.convertToDomain(entity);
        study.setGroup(GroupHelper.convertToDomain(entity.getGroup()));
        study.setLecturer(LecturerHelper.convertToDomain(entity.getLecturer()));
        study.setCurriculum(CurriculumHelper.convertToDomain(entity.getCurriculum()));
        return study;
    }

}
