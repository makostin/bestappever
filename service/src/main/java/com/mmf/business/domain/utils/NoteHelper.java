package com.mmf.business.domain.utils;

import com.mmf.business.domain.Note;
import com.mmf.db.model.NoteEntity;

/**
 * svetlana.voyteh
 * 13.03.13
 */
public class NoteHelper {

    /**
     * Convert Note to Note entity.
     *
     * @param domain Note
     * @param entity NoteEntity
     */
    public static void convertToEntity(Note domain, NoteEntity entity) {
        if (entity != null) {
            entity.setColor(domain.getColor());
            entity.setText(domain.getText());
            entity.setDate(domain.getDate());
        }
    }

    /**
     * Convert Note entity to Note.
     *
     * @param entity NoteEntity
     * @return Note
     */
    public static Note convertToDomain(NoteEntity entity) {
        Note domain = new Note();
        domain.setId(entity.getId());
        domain.setColor(entity.getColor());
        domain.setDate(entity.getDate());
        domain.setText(entity.getText());
        domain.setScheduleId(entity.getSchedule().getId());
        domain.setUserId(entity.getUser().getId());
        return domain;
    }
}
