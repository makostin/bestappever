package com.mmf.db.dao.impl;

import android.content.ContentValues;
import android.database.Cursor;
import com.mmf.db.DaoLayerException;
import com.mmf.db.dao.AbstractEntityDao;
import com.mmf.db.model.Filter;
import com.mmf.db.model.Schedule;
import com.mmf.rest.exceptions.ServiceLayerException;
import com.mmf.util.EntityRegistry;

/**
 * User: svetlana.voyteh
 * Date: 19.03.13
 */
public class FilterDao extends AbstractEntityDao<Filter>{

    public static final String TABLE_NAME = "filter";

    public static final String COURSE_COLUMN = "course";
    public static final String GROUP_COLUMN = "group";
    public static final String SUBGROUP_COLUMN = "subGroup";
    public static final String LECTURER_COLUMN = "idLecturer";

    public FilterDao() {
        super(TABLE_NAME, DATABASE_VERSION_1);
        init();
    }

    private void init() {
        addColumnV1(COURSE_COLUMN, "int");
        addColumnV1(GROUP_COLUMN, "int");
        addColumnV1(SUBGROUP_COLUMN, "text");
        addColumnV1(LECTURER_COLUMN, "long");
    }

    private void addColumnV1(String name, String def) {
        addColumn(name, def, DATABASE_VERSION_1);
    }

    @Override
    protected Filter _entityFrom(Cursor cursor) {
        Filter result = new Filter();

        result.setCourse(getInt(cursor, COURSE_COLUMN));
        result.setGroupNumber(getInt(cursor, GROUP_COLUMN));
        result.setSubGroup(getString(cursor, SUBGROUP_COLUMN));
        result.setLecturerId(getLong(cursor, LECTURER_COLUMN));
        return result;
    }

    @Override
    protected void _entityTo(Filter entity, ContentValues values) {
        put(values, COURSE_COLUMN, entity.getCourse());
        put(values, GROUP_COLUMN, entity.getGroupNumber());
        put(values, SUBGROUP_COLUMN, entity.getSubGroup());
        put(values, LECTURER_COLUMN, entity.getLecturerId());
    }

    private Filter getFilter(int course, int group, String subGroup) {
        StringBuilder whereClause = new StringBuilder(COURSE_COLUMN).append(" = ?");
        whereClause.append(" AND ").append(GROUP_COLUMN).append(" = ?");
        whereClause.append(" AND ").append(SUBGROUP_COLUMN).append(" = ?");
        String[] whereArgs = new String[]{String.valueOf(course), String.valueOf(group), subGroup};
        return getEntityQuery(whereClause.toString(), whereArgs);
    }

    public void updateFilter(int course, int group, String subGroup) throws ServiceLayerException {
        try {
            Filter filter = getFilter(course, group, subGroup);
            if (filter != null){
                delete(filter.getId());
                ScheduleDao scheduleDao = (ScheduleDao) EntityRegistry.get().getEntityDao(Schedule.class);
                scheduleDao.deleteScheduleByFilter(filter.getId());
            }
            // todo: setId(null)!!!
            filter.setId(null);
            saveData(filter);
        } catch (DaoLayerException e) {
            throw new ServiceLayerException(e);
        }
    }
}
