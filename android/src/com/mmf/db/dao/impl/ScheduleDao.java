package com.mmf.db.dao.impl;

import android.content.ContentValues;
import android.database.Cursor;
import com.mmf.db.dao.AbstractEntityDao;
import com.mmf.db.model.Schedule;

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
    public static final String SUPGROUP_COLUMN = "supGroup";
    public static final String DAY_COLUMN = "day";
    public static final String DATE_COLUMN = "date";
    public static final String NUMBER_COLUMN = "number";
    public static final String ID_LECTURER_COLUMN = "idLecturer";

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
        addColumnV1(SUPGROUP_COLUMN, "text");
        addColumnV1(DAY_COLUMN, "int");
        addColumnV1(DATE_COLUMN, "long");
        addColumnV1(NUMBER_COLUMN, "int");
        addColumnV1(ID_LECTURER_COLUMN, "long");
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
        result.setDate(getLong(cursor, DATE_COLUMN));
        result.setGroupNumber(getInt(cursor, GROUP_NUMBER_COLUMN));
        result.setNumber(getInt(cursor, NUMBER_COLUMN));
        result.setSupGroup(getString(cursor, SUPGROUP_COLUMN));
        result.setLecturerId(getLong(cursor, ID_LECTURER_COLUMN));
        return result;
    }

    @Override
    protected void _entityTo(Schedule entity, ContentValues values) {
        put(values, TIME_COLUMN, entity.getTime());
        put(values, CLASSROOM_COLUMN, entity.getClassroom());
        put(values, COURSE_COLUMN, entity.getCourse());
        put(values, DISCIPLINE_COLUMN, entity.getDiscipline());
        put(values, DAY_COLUMN, entity.getDay());
        put(values, DATE_COLUMN, entity.getDate());
        put(values, GROUP_NUMBER_COLUMN, entity.getGroupNumber());
        put(values, NUMBER_COLUMN, entity.getNumber());
        put(values, SUPGROUP_COLUMN, entity.getSupGroup());
        put(values, ID_LECTURER_COLUMN, entity.getLecturerId());
    }
}
