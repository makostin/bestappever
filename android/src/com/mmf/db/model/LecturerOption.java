package com.mmf.db.model;

import android.content.ContentValues;
import android.database.Cursor;
import com.mmf.db.Entity;

/**
 * @author svetlana.voyteh
 * @date: 4/2/12
 */
public class LecturerOption extends Entity {

    public final static String TABLE_NAME = "lecturerOption";

    public final static String COLUMN_LECTURER_ID = "lecturer_id";
    public final static String COLUMN_DEPARTMENT = "department";
    public final static String COLUMN_NAME = "name";
    public final static String COLUMN_WEEK = "week";
    public final static String COLUMN_IS_DOWNLOAD = "is_download";
    public final static String COLUMN_IS_LOGIN = "is_login";
    public final static String COLUMN_IS_CURRENT = "is_current";

    private Long lecturerId;
    private String department;
    private String name;
    private int week;
    private boolean isDownload;
    private boolean isLogin;
    private boolean isCurrent;


    public Long getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(Long lecturerId) {
        this.lecturerId = lecturerId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
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
        return new String[] { getPrimaryKeyColumnName(), COLUMN_LECTURER_ID, COLUMN_NAME, COLUMN_DEPARTMENT, COLUMN_IS_CURRENT,
                COLUMN_IS_DOWNLOAD, COLUMN_IS_LOGIN, COLUMN_WEEK };
    }

    @Override
    public ContentValues getContentValues(boolean isUpdate) {
        ContentValues contentValues = new ContentValues();
        if (isUpdate){
            contentValues.put(getPrimaryKeyColumnName(), getId());
        }
        contentValues.put(COLUMN_LECTURER_ID, lecturerId);
        contentValues.put(COLUMN_WEEK, week);
        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_DEPARTMENT, department);
        contentValues.put(COLUMN_IS_CURRENT, isCurrent ? 1 : 0);
        contentValues.put(COLUMN_IS_DOWNLOAD, isDownload ? 1 : 0);
        contentValues.put(COLUMN_IS_LOGIN, isLogin ? 1 : 0);
        return contentValues;
    }

    @Override
    public void extractValues(Cursor cursor) {
        id = cursor.getInt(cursor.getColumnIndex(getPrimaryKeyColumnName()));
        lecturerId = cursor.getLong(cursor.getColumnIndex(COLUMN_LECTURER_ID));
        department = cursor.getString(cursor.getColumnIndex(COLUMN_DEPARTMENT));
        name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
        week = cursor.getInt(cursor.getColumnIndex(COLUMN_WEEK));
        isCurrent = cursor.getInt(cursor.getColumnIndex(COLUMN_IS_CURRENT)) == 1;
        isDownload = cursor.getInt(cursor.getColumnIndex(COLUMN_IS_DOWNLOAD)) == 1;
        isLogin = cursor.getInt(cursor.getColumnIndex(COLUMN_IS_LOGIN)) == 1;
    }

}
