package com.mmf.business.impl;

import com.mmf.business.BusinessServiceException;
import com.mmf.business.NoteService;
import com.mmf.business.domain.Note;
import com.mmf.business.domain.utils.NoteHelper;
import com.mmf.business.domain.utils.ScheduleHelper;
import com.mmf.business.domain.utils.UserHelper;
import com.mmf.db.dao.DataAccessException;
import com.mmf.db.dao.NoteDao;
import com.mmf.db.dao.ScheduleDao;
import com.mmf.db.dao.UserDao;
import com.mmf.db.model.NoteEntity;
import com.mmf.db.model.ScheduleEntity;
import com.mmf.db.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;

/**
 * User: svetlana.voyteh
 * Date: 20.03.13
 */
@Named
public class NoteServiceImpl extends AbstractCrudService<Long, Note, NoteEntity, NoteDao> implements NoteService {

    @Autowired
    private NoteDao noteDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ScheduleDao scheduleDao;

    @Override
    protected NoteDao getDao() {
        return noteDao;
    }

    @Override
    public void convertToEntity(Note domain, NoteEntity entity) throws BusinessServiceException {
        if (domain != null) {
            try {
                NoteHelper.convertToEntity(domain, entity);
                UserEntity userEntity = userDao.getEntityInstance(domain.getUserId());
                if (userEntity == null) {
                    throw new BusinessServiceException("Such user doesn't exist.");
                }

                if (entity != null) {
                    entity.setUser(userEntity);
                }

                ScheduleEntity scheduleEntity = scheduleDao.getEntityInstance(domain.getScheduleId());
                if (scheduleEntity == null) {
                    throw new BusinessServiceException("Such schedule doesn't exist.");
                }

                if (entity != null) {
                    entity.setSchedule(scheduleEntity);
                }
            } catch (DataAccessException e) {
                throw new BusinessServiceException("Conversion to note entity error.", e);
            }

        }
    }

    @Override
    public Note convertToDomain(NoteEntity entity) throws BusinessServiceException {
        if (entity == null) {
            return null;
        }

        Note note = NoteHelper.convertToDomain(entity);
        note.setUser(UserHelper.convertToDomain(entity.getUser()));
        note.setSchedule(ScheduleHelper.convertToDomain(entity.getSchedule()));

        return note;
    }
}
