package com.mmf.db.dao.jpa;

import com.mmf.db.dao.NoteDao;
import com.mmf.db.model.NoteEntity;

import javax.inject.Named;

/**
 * User: svetlana.voyteh
 * Date: 20.03.13
 */
@Named
public class NoteDaoImpl extends GenericJpaDao<Long, NoteEntity> implements NoteDao {

    @Override
    protected Class<NoteEntity> getEntityClass() {
        return NoteEntity.class;
    }
}
