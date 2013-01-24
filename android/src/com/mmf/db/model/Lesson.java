package com.mmf.db.model;

import android.content.ContentValues;
import android.database.Cursor;
import com.mmf.db.Entity;
import org.ksoap2.serialization.SoapObject;

import java.util.Date;

/**
 * @author Maxim Kostin (kostinmaks@gmail.com)
 * @date 1/19/12
 */
public class Lesson extends Entity{

    public static final String TABLE_NAME = "lesson";

    public static final String COLUMN_SUBJECT = "subject";
    public static final String COLUMN_CLASSROOM = "classroom";
    public static final String COLUMN_TIME = "time";
    public static final String COLUMN_LECTURER = "lecturer";
    public static final String COLUMN_DAY = "day";
    public static final String COLUMN_COURSE = "course";
    public static final String COLUMN_SUBGROUP = "subGroup";
    public static final String COLUMN_LESSON_ID = "lessonId";

    private Long lessonId;
    private String subject;
    private String classRoom;
//    private Date date;
    private String time;
    private String lecturer;
    private int day;
    private int course;
    private String subgroup;

    public Lesson() {
    }

    public Lesson(String subject, String classRoom, String time, String lecturer, int day) {
        this.subject = subject;
        this.classRoom = classRoom;
        this.time = time;
        this.lecturer = lecturer;
        this.day = day;
    }

    public Long getLessonId() {
        return lessonId;
    }

    public void setLessonId(Long lessonId) {
        this.lessonId = lessonId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }

//    public Date getDate() {
//        return date;
//    }
//
//    public void setDate(Date date) {
//        this.date = date;
//    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLecturer() {
        return lecturer;
    }

    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
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

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public String[] getColumns() {
        return new String[] { getPrimaryKeyColumnName(), COLUMN_LESSON_ID, COLUMN_SUBJECT, COLUMN_CLASSROOM,
                COLUMN_TIME, COLUMN_LECTURER, COLUMN_DAY, COLUMN_COURSE, COLUMN_SUBGROUP };
    }

    @Override
    public ContentValues getContentValues(boolean isUpdate) {
        ContentValues contentValues = new ContentValues();
        if (isUpdate){
            contentValues.put(getPrimaryKeyColumnName(), id);
        }
        contentValues.put(COLUMN_LESSON_ID, lessonId);
        contentValues.put(COLUMN_SUBJECT, subject);
        contentValues.put(COLUMN_CLASSROOM, classRoom);
        contentValues.put(COLUMN_TIME, time);
        contentValues.put(COLUMN_LECTURER, lecturer);
        contentValues.put(COLUMN_DAY, day);
        contentValues.put(COLUMN_COURSE, course);
        contentValues.put(COLUMN_SUBGROUP, subgroup);
        return contentValues;
    }

    @Override
    public void extractValues(Cursor cursor) {
        id = cursor.getInt(cursor.getColumnIndex(getPrimaryKeyColumnName()));
        lessonId = cursor.getLong(cursor.getColumnIndex(COLUMN_LESSON_ID));
        subject = cursor.getString(cursor.getColumnIndex(COLUMN_SUBJECT));
        classRoom = cursor.getString(cursor.getColumnIndex(COLUMN_CLASSROOM));
        time = cursor.getString(cursor.getColumnIndex(COLUMN_TIME));
        lecturer = cursor.getString(cursor.getColumnIndex(COLUMN_LECTURER));
        day = cursor.getInt(cursor.getColumnIndex(COLUMN_DAY));
        course = cursor.getInt(cursor.getColumnIndex(COLUMN_COURSE));
        subgroup = cursor.getString(cursor.getColumnIndex(COLUMN_SUBGROUP));
    }
}
