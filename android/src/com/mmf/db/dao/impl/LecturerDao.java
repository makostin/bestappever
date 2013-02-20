package com.mmf.db.dao.impl;

import android.content.ContentValues;
import android.database.Cursor;
import com.mmf.db.dao.AbstractEntityDao;
import com.mmf.db.model.Lecturer;

/**
 * svetlana.voyteh
 * 13.02.13
 */
public class LecturerDao extends AbstractEntityDao<Lecturer>{

    public static final String TABLE_NAME = "lecturer";

    public static final String FULLNAME_COLUMN = "fullname";
    public static final String ID_DEPARTMENT_COLUMN = "idDepartment";
    public static final String SYSTEM_ID_COLUMN = "systemId";

    public LecturerDao() {
        super(TABLE_NAME, DATABASE_VERSION_1);
        init();
    }

    private void init() {
        addColumnV1(FULLNAME_COLUMN, "text not null");
        addColumnV1(ID_DEPARTMENT_COLUMN, "long");
        addColumnV1(SYSTEM_ID_COLUMN, "long");
    }

    private void addColumnV1(String name, String def) {
        addColumn(name, def, DATABASE_VERSION_1);
    }

    @Override
    protected Lecturer _entityFrom(Cursor cursor) {
        Lecturer result = new Lecturer();

        result.setFullName(getString(cursor, FULLNAME_COLUMN));
        result.setDepartmentId(getLong(cursor, ID_DEPARTMENT_COLUMN));
        result.setSystemId(getLong(cursor, SYSTEM_ID_COLUMN));
        return result;
    }

    @Override
    protected void _entityTo(Lecturer entity, ContentValues values) {
        put(values, FULLNAME_COLUMN, entity.getFullName());
        put(values, ID_DEPARTMENT_COLUMN, entity.getDepartmentId());
        put(values, SYSTEM_ID_COLUMN, entity.getSystemId());
    }
}
