package com.mmf.business.impl;

import com.mmf.business.BusinessServiceException;
import com.mmf.business.ClassroomService;
import com.mmf.business.CurriculumService;
import com.mmf.business.domain.Classroom;
import com.mmf.business.domain.Curriculum;
import com.mmf.business.domain.utils.ClassroomHelper;
import com.mmf.business.domain.utils.CurriculumHelper;
import com.mmf.business.domain.utils.DisciplineHelper;
import com.mmf.business.domain.utils.SpecialtyHelper;
import com.mmf.db.dao.*;
import com.mmf.db.model.ClassroomEntity;
import com.mmf.db.model.CurriculumEntity;
import com.mmf.db.model.DisciplineEntity;
import com.mmf.db.model.SpecialtyEntity;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;

/**
 * User: svetlana.voyteh
 * Date: 20.03.13
 */
@Named
public class CurriculumServiceImpl extends AbstractCrudService<Long, Curriculum, CurriculumEntity, CurriculumDao> implements CurriculumService{

    @Autowired
    private CurriculumDao curriculumDao;

    @Autowired
    private SpecialtyDao specialtyDao;

    @Autowired
    private DisciplineDao disciplineDao;

    @Override
    protected CurriculumDao getDao() {
        return curriculumDao;
    }

    @Override
    public void convertToEntity(Curriculum domain, CurriculumEntity entity) throws BusinessServiceException {
        if (domain != null){
            try {
                CurriculumHelper.convertToEntity(domain, entity);

                SpecialtyEntity specialtyEntity = specialtyDao.getEntityInstance(domain.getSpecialtyId());
                if(specialtyEntity == null){
                    throw new BusinessServiceException("Such specialty doesn't exist.");
                }

                if (entity != null){
                    entity.setSpecialty(specialtyEntity);
                }

                DisciplineEntity disciplineEntity = disciplineDao.getEntityInstance(domain.getDisciplineId());
                if(disciplineEntity == null){
                    throw new BusinessServiceException("Such discipline doesn't exist.");
                }

                if(entity != null){
                    entity.setDiscipline(disciplineEntity);
                }
            } catch (DataAccessException e) {
                throw new BusinessServiceException("Conversion to curriculum entity error.", e);
            }

        }
    }

    @Override
    public Curriculum convertToDomain(CurriculumEntity entity) throws BusinessServiceException {
        if(entity == null){
            return null;
        }

        Curriculum curriculum = CurriculumHelper.convertToDomain(entity);
        curriculum.setDiscipline(DisciplineHelper.convertToDomain(entity.getDiscipline()));
        curriculum.setSpecialty(SpecialtyHelper.convertToDomain(entity.getSpecialty()));

        return curriculum;
    }
}
