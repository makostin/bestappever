package com.mmf.db.model;

import android.content.ContentValues;
import android.database.Cursor;
import com.mmf.db.Entity;

/**
 * @author svetlana.voyteh
 * @date: 2/16/12
 */
public class StudentOption extends Entity{
    
    public final static String TABLE_NAME = "studentOption";

    public final static String COLUMN_COURSE = "course";
    public final static String COLUMN_SUBGROUP = "subGroup";
    public final static String COLUMN_NAME = "name";
    public final static String COLUMN_WEEK = "week";
    public final static String COLUMN_IS_DOWNLOAD = "is_download";
    public final static String COLUMN_IS_LOGIN = "is_login";
    public final static String COLUMN_IS_CURRENT = "is_current";

    private int course;
    private String subgroup;
    private String name;
    private int week;
    private boolean isDownload;
    private boolean isLogin;
    private boolean isCurrent;
    

    public StudentOption() {
    }

    public StudentOption(int course, String subgroup) {
        this.course = course;
        this.subgroup = subgroup;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public String getSubgroup() {
        return subgroup;
    }

    public void setSubgroup(String subgroup) {
        this.subgroup = subgroup;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public boolean isDownload() {
        return isDownload;
    }

    public void setDownload(boolean download) {
        isDownload = download;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public boolean isCurrent() {
        return isCurrent;
    }

    public void setCurrent(boolean current) {
        isCurrent = current;
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public String[] getColumns() {
        return new String[] { getPrimaryKeyColumnName(), COLUMN_NAME, COLUMN_WEEK, COLUMN_IS_CURRENT,
                COLUMN_IS_DOWNLOAD, COLUMN_IS_LOGIN, COLUMN_COURSE, COLUMN_SUBGROUP };
    }

    @Override
    public ContentValues getContentValues(boolean isUpdate) {
        ContentValues contentValues = new ContentValues();
        if (isUpdate){
            contentValues.put(getPrimaryKeyColumnName(), getId());
        }
        contentValues.put(COLUMN_COURSE, course);
        contentValues.put(COLUMN_SUBGROUP, subgroup);
        contentValues.put(COLUMN_WEEK, week);
        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_IS_CURRENT, isCurrent ? 1 : 0);
        contentValues.put(COLUMN_IS_DOWNLOAD, isDownload ? 1 : 0);
        contentValues.put(COLUMN_IS_LOGIN, isLogin ? 1 : 0);
        return contentValues;
    }

    @Override
    public void extractValues(Cursor cursor) {
        id = cursor.getInt(cursor.getColumnIndex(getPrimaryKeyColumnName()));
        course = cursor.getInt(cursor.getColumnIndex(COLUMN_COURSE));
        subgroup = cursor.getString(cursor.getColumnIndex(COLUMN_SUBGROUP));
        name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
        week = cursor.getInt(cursor.getColumnIndex(COLUMN_WEEK));
        isCurrent = cursor.getInt(cursor.getColumnIndex(COLUMN_IS_CURRENT)) == 1;
        isDownload = cursor.getInt(cursor.getColumnIndex(COLUMN_IS_DOWNLOAD)) == 1;
        isLogin = cursor.getInt(cursor.getColumnIndex(COLUMN_IS_LOGIN)) == 1;
    }

}
