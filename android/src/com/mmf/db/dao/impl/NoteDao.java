package com.mmf.db.dao.impl;

import android.content.ContentValues;
import android.database.Cursor;
import com.mmf.db.dao.AbstractEntityDao;
import com.mmf.db.model.Note;

/**
 * svetlana.voyteh
 * 13.02.13
 */
public class NoteDao extends AbstractEntityDao<Note>{
    
    public static final String TABLE_NAME = "note";
    
    public static final String DATA_COLUMN = "data";
    public static final String ID_SCHEDULE_COLUMN = "idSchedule";
    
    public NoteDao() {
        super(TABLE_NAME, DATABASE_VERSION_1);
        init();
    }

    private void init() {
        addColumnV1(DATA_COLUMN, "text not null");
        addColumnV1(ID_SCHEDULE_COLUMN, "long");
    }

    private void addColumnV1(String name, String def) {
        addColumn(name, def, DATABASE_VERSION_1);
    }

    @Override
    protected Note _entityFrom(Cursor cursor) {
        Note result = new Note();

        result.setData(getString(cursor, DATA_COLUMN));
        result.setScheduleId(getLong(cursor, ID_SCHEDULE_COLUMN));
        return null; 
    }

    @Override
    protected void _entityTo(Note entity, ContentValues values) {
        put(values, DATA_COLUMN, entity.getData());
        put(values, ID_SCHEDULE_COLUMN, entity.getScheduleId());
    }
}
