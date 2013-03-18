package com.mmf.db.dao.impl;

import android.content.ContentValues;
import android.database.Cursor;
import com.mmf.db.dao.AbstractEntityDao;
import com.mmf.db.model.Specialty;

/**
 * svetlana.voyteh
 * 13.02.13
 */
public class SpecialtyDao extends AbstractEntityDao<Specialty>{

    public static final String TABLE_NAME = "specialty";

    public static final String NAME_COLUMN = "name";
    public static final String NUMBER_COLUMN = "number";


    public SpecialtyDao() {
        super(TABLE_NAME, DATABASE_VERSION_1);
        init();
    }

    private void init() {
        addColumnV1(NAME_COLUMN, "text not null");
        addColumnV1(NUMBER_COLUMN, "text not null");
    }


    private void addColumnV1(String name, String def) {
        addColumn(name, def, DATABASE_VERSION_1);
    }

    @Override
    protected Specialty _entityFrom(Cursor cursor) {
        Specialty result = new Specialty();

        result.setName(getString(cursor, NAME_COLUMN));
        result.setNumber(getInt(cursor, NUMBER_COLUMN));
        return result;
    }

    @Override
    protected void _entityTo(Specialty entity, ContentValues values) {
        put(values, NAME_COLUMN, entity.getName());
        put(values, NUMBER_COLUMN, entity.getNumber());
    }

    public Specialty getSpecialtyByGroupNumber(int group) {
        String whereClause = NUMBER_COLUMN + "= ?";
        String[] whereArgs = new String[]{String.valueOf(group)};
        return getEntityQuery(whereClause, whereArgs);
    }
}
