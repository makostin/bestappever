package com.mmf.db.dao.impl;

import android.content.ContentValues;
import android.database.Cursor;
import com.mmf.db.dao.AbstractEntityDao;
import com.mmf.db.model.Department;

/**
 * svetlana.voyteh
 * 13.02.13
 */
public class DepartmentDao extends AbstractEntityDao<Department>{

    public static final String TABLE_NAME = "department";

    public static final String NAME_COLUMN = "name";

    public DepartmentDao() {
        super(TABLE_NAME, DATABASE_VERSION_1);
        init();
    }

    private void init() {
        addColumnV1(NAME_COLUMN, "text not null");
    }

    private void addColumnV1(String name, String def) {
        addColumn(name, def, DATABASE_VERSION_1);
    }

    @Override
    protected Department _entityFrom(Cursor cursor) {
        Department result = new Department();

        result.setName(getString(cursor, NAME_COLUMN));
        return result;
    }

    @Override
    protected void _entityTo(Department entity, ContentValues values) {
        put(values, NAME_COLUMN, entity.getName());
    }

    public Department getByName(String departmentName) {
        String whereClause = NAME_COLUMN + "= ?";
        String[] whereArgs = new String[]{departmentName};
        return getEntityQuery(whereClause, whereArgs);
    }
}
