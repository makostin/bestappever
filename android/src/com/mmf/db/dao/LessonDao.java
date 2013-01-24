package com.mmf.db.dao;

import com.mmf.db.DaoLayerException;
import com.mmf.db.EntityManager;
import com.mmf.db.TransactionException;
import com.mmf.db.model.Lesson;
import com.mmf.util.Logger;
import com.mmf.util.StringUtils;

import java.util.List;

/**
 * @author svetlana.voyteh
 * @date: 2/10/12
 */
public class LessonDao {

    private final EntityManager entityManager = EntityManager.getInstance();

    public LessonDao() {
    }

    public boolean isLessonsExist(int course, String subgroup) throws DaoLayerException{
        try {
            String selection = StringUtils.getSelectionString(new String[]{Lesson.COLUMN_COURSE, Lesson.COLUMN_SUBGROUP});
            String[] selectionArgs = new String[]{String.valueOf(course), subgroup};
            return entityManager.get(Lesson.class, selection, selectionArgs) != null;
        } catch (InstantiationException e) {
            Logger.getInstance().error(e);
            throw new DaoLayerException(e);
        } catch (IllegalAccessException e) {
            Logger.getInstance().error(e);
            throw new DaoLayerException(e);
        }
    }

    public boolean isLessonsExist(String lecturer) throws DaoLayerException {
        try {
            String selection = StringUtils.getSelectionString(new String[]{Lesson.COLUMN_LECTURER});
            String[] selectionArgs = new String[]{lecturer};
            return entityManager.get(Lesson.class, selection, selectionArgs) != null;
        } catch (InstantiationException e) {
            Logger.getInstance().error(e);
            throw new DaoLayerException(e);
        } catch (IllegalAccessException e) {
            Logger.getInstance().error(e);
            throw new DaoLayerException(e);
        }
    }

    public List<Lesson> getLessonsForDay(int course, String subgroup, int day) throws DaoLayerException {
        try {
            String selection = StringUtils.getSelectionString(new String[]{Lesson.COLUMN_DAY, Lesson.COLUMN_COURSE, Lesson.COLUMN_SUBGROUP});
            String[] selectionArgs = new String[]{String.valueOf(day), String.valueOf(course), subgroup};
            String orderBy = Lesson.COLUMN_TIME;
            return entityManager.find(Lesson.class, selection, selectionArgs, orderBy, null);
        } catch (InstantiationException e) {
            Logger.getInstance().error(e);
            throw new DaoLayerException(e);
        } catch (IllegalAccessException e) {
            Logger.getInstance().error(e);
            throw new DaoLayerException(e);
        }
    }

    public List<Lesson> getLessonsForDay(String lecturer, int currentDay) throws DaoLayerException {
        try {
            String selection = StringUtils.getSelectionString(new String[]{Lesson.COLUMN_DAY, Lesson.COLUMN_LECTURER});
            String[] selectionArgs = new String[]{String.valueOf(currentDay), lecturer};
            String orderBy = Lesson.COLUMN_TIME;
            return entityManager.find(Lesson.class, selection, selectionArgs, orderBy, null);
        } catch (InstantiationException e) {
            Logger.getInstance().error(e);
            throw new DaoLayerException(e);
        } catch (IllegalAccessException e) {
            Logger.getInstance().error(e);
            throw new DaoLayerException(e);
        }
    }

    public void insert(Lesson lesson) throws DaoLayerException {
        try {
            entityManager.beginTransaction();
            entityManager.insert(lesson);
            entityManager.commitTransaction();
        } catch (TransactionException e) {
            entityManager.rollbackTransaction();
            Logger.getInstance().error(e);
            throw new DaoLayerException(e);
        }
    }

    public void deleteLessons(int course, String subgroup) throws DaoLayerException {
        try {
            String whereClause = StringUtils.getSelectionString(new String[]{Lesson.COLUMN_COURSE, Lesson.COLUMN_SUBGROUP});
            String[] whereArgs = new String[]{String.valueOf(course), subgroup};
            entityManager.beginTransaction();
            entityManager.delete(Lesson.class, whereClause, whereArgs);
            entityManager.commitTransaction();
        } catch (InstantiationException e) {
            Logger.getInstance().error(e);
            throw new DaoLayerException(e);
        } catch (IllegalAccessException e) {
            Logger.getInstance().error(e);
            throw new DaoLayerException(e);
        } catch (TransactionException e) {
            entityManager.rollbackTransaction();
            Logger.getInstance().error(e);
            throw new DaoLayerException(e);
        }
    }

    public void deleteLessons(String lecturer) throws DaoLayerException {
        try {
            String whereClause = StringUtils.getSelectionString(new String[]{Lesson.COLUMN_LECTURER});
            String[] whereArgs = new String[]{lecturer};
            entityManager.beginTransaction();
            entityManager.delete(Lesson.class, whereClause, whereArgs);
            entityManager.commitTransaction();
        } catch (InstantiationException e) {
            Logger.getInstance().error(e);
            throw new DaoLayerException(e);
        } catch (IllegalAccessException e) {
            Logger.getInstance().error(e);
            throw new DaoLayerException(e);
        } catch (TransactionException e) {
            entityManager.rollbackTransaction();
            Logger.getInstance().error(e);
            throw new DaoLayerException(e);
        }
    }

//    public void deleteAll() throws DaoLayerException {
//        try {
//            entityManager.beginTransaction();
//            entityManager.delete(Lesson.class, null, null);
//            entityManager.commitTransaction();
//        } catch (InstantiationException e) {
//            Logger.getInstance().error(e);
//            throw new DaoLayerException(e);
//        } catch (IllegalAccessException e) {
//            Logger.getInstance().error(e);
//            throw new DaoLayerException(e);
//        } catch (TransactionException e) {
//            entityManager.rollbackTransaction();
//            Logger.getInstance().error(e);
//            throw new DaoLayerException(e);
//        }
//    }



}
