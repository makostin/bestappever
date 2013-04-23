package com.mmf.db.dao.impl;

import android.content.ContentValues;
import android.database.Cursor;
import com.mmf.db.dao.AbstractEntityDao;
import com.mmf.db.model.Lecturer;
import com.mmf.db.model.Schedule;
import com.mmf.util.Logger;

import java.util.List;

/**
 * svetlana.voyteh
 * 13.02.13
 */
public class ScheduleDao extends AbstractEntityDao<Schedule>{

    public static final String TABLE_NAME = "schedule";

    public static final String TIME_COLUMN = "time";
    public static final String CLASSROOM_COLUMN = "classroom";
    public static final String DISCIPLINE_COLUMN = "discipline";
    public static final String GROUP_NUMBER_COLUMN = "groupNumber";
    public static final String COURSE_COLUMN = "course";
    public static final String SUBGROUP_COLUMN = "subGroup";
    public static final String DAY_COLUMN = "day";
    public static final String WEEK_COLUMN = "week";
    public static final String NUMBER_COLUMN = "number";
    public static final String LECTURER_COLUMN = "idLecturer";
    public static final String FILTER_COLUMN = "idFilter";

    public ScheduleDao() {
        super(TABLE_NAME, DATABASE_VERSION_1);
        init();
    }

    private void init() {
        addColumnV1(TIME_COLUMN, "text not null");
        addColumnV1(CLASSROOM_COLUMN, "int");
        addColumnV1(DISCIPLINE_COLUMN, "text not null");
        addColumnV1(GROUP_NUMBER_COLUMN, "int");
        addColumnV1(COURSE_COLUMN, "int");
        addColumnV1(SUBGROUP_COLUMN, "text");
        addColumnV1(DAY_COLUMN, "int");
        addColumnV1(WEEK_COLUMN, "int");
        addColumnV1(NUMBER_COLUMN, "int");
        addColumnV1(LECTURER_COLUMN, "long");
        addColumnV1(FILTER_COLUMN, "long");
    }

    private void addColumnV1(String name, String def) {
        addColumn(name, def, DATABASE_VERSION_1);
    }

    @Override
    protected Schedule _entityFrom(Cursor cursor) {
        Schedule result = new Schedule();

        result.setTime(getString(cursor, TIME_COLUMN));
        result.setClassroom(getInt(cursor, CLASSROOM_COLUMN));
        result.setCourse(getInt(cursor, COURSE_COLUMN));
        result.setDiscipline(getString(cursor, DISCIPLINE_COLUMN));
        result.setDay(getInt(cursor, DAY_COLUMN));
        result.setWeek(getInt(cursor, WEEK_COLUMN));
        result.setGroupNumber(getInt(cursor, GROUP_NUMBER_COLUMN));
        result.setNumber(getInt(cursor, NUMBER_COLUMN));
        result.setSubGroup(getString(cursor, SUBGROUP_COLUMN));
        result.setLecturer(new Lecturer(getLong(cursor, LECTURER_COLUMN)));
        result.setFilterId(getLong(cursor, FILTER_COLUMN));
        return result;
    }

    @Override
    protected void _entityTo(Schedule entity, ContentValues values) {
        put(values, TIME_COLUMN, entity.getTime());
        put(values, CLASSROOM_COLUMN, entity.getClassroom());
        put(values, COURSE_COLUMN, entity.getCourse());
        put(values, DISCIPLINE_COLUMN, entity.getDiscipline());
        put(values, DAY_COLUMN, entity.getDay());
        put(values, WEEK_COLUMN, entity.getWeek());
        put(values, GROUP_NUMBER_COLUMN, entity.getGroupNumber());
        put(values, NUMBER_COLUMN, entity.getNumber());
        put(values, SUBGROUP_COLUMN, entity.getSubGroup());
        put(values, LECTURER_COLUMN, entity.getLecturer().getId());
        put(values, FILTER_COLUMN, entity.getFilterId());
    }

    public void deleteScheduleByFilter(Long filterId) {
        String whereClause = FILTER_COLUMN + " = ?";
        String[] whereArgs = new String[]{String.valueOf(filterId)};
        int rows = deleteEntityQuery(whereClause, whereArgs);
        Logger.getInstance().debug("Amount of deleted rows: " + rows);
    }

    public List<Schedule> getLessonsForDay(long filterId, int currentDay, int week) {
        StringBuilder whereClause = new StringBuilder(FILTER_COLUMN).append(" = ? AND ");
        whereClause.append(DAY_COLUMN).append(" = ? AND ");
        whereClause.append("(").append(WEEK_COLUMN).append(" = ? OR ").append(WEEK_COLUMN).append(" = 0)");
        String[] whereArgs = new String[]{String.valueOf(filterId), String.valueOf(currentDay), String.valueOf(week)};
        return getEntityListQuery(whereClause.toString(), whereArgs);
    }

}
