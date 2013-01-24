package com.mmf.db.dao;

import com.mmf.db.DaoLayerException;
import com.mmf.db.EntityManager;
import com.mmf.db.TransactionException;
import com.mmf.db.model.LecturerOption;
import com.mmf.db.model.StudentOption;
import com.mmf.util.Logger;
import com.mmf.util.StringUtils;

import java.util.List;

/**
 * @author svetlana.voyteh
 * @date: 4/2/12
 */
public class LecturerOptionDao {

    private final EntityManager entityManager = EntityManager.getInstance();

    public LecturerOptionDao() {
    }

//    public List<LecturerOption> getAll() throws DaoLayerException {
//        try {
//            return entityManager.find(LecturerOption.class, null, null, LecturerOption.COLUMN_DEPARTMENT, null);
//        } catch (InstantiationException e) {
//            Logger.getInstance().error(e);
//            throw new DaoLayerException(e);
//        } catch (IllegalAccessException e) {
//            Logger.getInstance().error(e);
//            throw new DaoLayerException(e);
//        }
//    }

    public List<String> getAllDepartments() throws DaoLayerException {
        try {
            return entityManager.find(LecturerOption.class, LecturerOption.COLUMN_DEPARTMENT, null, null, LecturerOption.COLUMN_DEPARTMENT, null);
        } catch (InstantiationException e) {
            Logger.getInstance().error(e);
            throw new DaoLayerException(e);
        } catch (IllegalAccessException e) {
            Logger.getInstance().error(e);
            throw new DaoLayerException(e);
        }
    }

    public List<String> getLecturersOfDepartment(String department) throws DaoLayerException {
        try {
            String selection = StringUtils.getSelectionString(new String[]{LecturerOption.COLUMN_DEPARTMENT});
            String[] selectionArgs = new String[]{department};
            return entityManager.find(LecturerOption.class, LecturerOption.COLUMN_NAME, selection, selectionArgs, LecturerOption.COLUMN_NAME, null);
        } catch (InstantiationException e) {
            Logger.getInstance().error(e);
            throw new DaoLayerException(e);
        } catch (IllegalAccessException e) {
            Logger.getInstance().error(e);
            throw new DaoLayerException(e);
        }
    }

    public void insert(LecturerOption lecturer) throws DaoLayerException {
        try {
            entityManager.beginTransaction();
            entityManager.insert(lecturer);
            entityManager.commitTransaction();
        } catch (TransactionException e){
            entityManager.rollbackTransaction();
            Logger.getInstance().error(e);
            throw new DaoLayerException(e);
        }
    }

    public boolean isEmpty() throws DaoLayerException {
        try {
            return entityManager.first(LecturerOption.class, LecturerOption.COLUMN_LECTURER_ID) == null;
        } catch (InstantiationException e) {
            Logger.getInstance().error(e);
            throw new DaoLayerException(e);
        } catch (IllegalAccessException e) {
            Logger.getInstance().error(e);
            throw new DaoLayerException(e);
        }
    }


    public LecturerOption getCurrent() throws DaoLayerException {
        try {
            String selection = "(is_current = 1)";
            return entityManager.get(LecturerOption.class, selection, null);
        } catch (InstantiationException e) {
            Logger.getInstance().error(e);
            throw new DaoLayerException(e);
        } catch (IllegalAccessException e) {
            Logger.getInstance().error(e);
            throw new DaoLayerException(e);
        }
    }


    public void update(LecturerOption lecturerOption) throws DaoLayerException {
        try {
            entityManager.beginTransaction();
            entityManager.update(lecturerOption);
            entityManager.commitTransaction();
        } catch (TransactionException e){
            entityManager.rollbackTransaction();
            Logger.getInstance().error(e);
            throw new DaoLayerException(e);
        }
    }

    public LecturerOption get(String department, String lecturer) throws DaoLayerException {
        try {
            String selection = StringUtils.getSelectionString(new String[]{LecturerOption.COLUMN_DEPARTMENT, LecturerOption.COLUMN_NAME});
            String[] selectionArgs = new String[]{department, lecturer};
            return entityManager.get(LecturerOption.class, selection, selectionArgs);
        } catch (InstantiationException e) {
            Logger.getInstance().error(e);
            throw new DaoLayerException(e);
        } catch (IllegalAccessException e) {
            Logger.getInstance().error(e);
            throw new DaoLayerException(e);
        }
    }

    public boolean isLessonsUpdate(int currentWeek) throws DaoLayerException {
        boolean result = false;
        try {
            String selection = "(is_current = 1)";
            LecturerOption lecturerOption = entityManager.get(LecturerOption.class, selection, null);
            if (lecturerOption != null) {
                if(lecturerOption.getWeek() < currentWeek || !lecturerOption.isDownload()){
                    result = true;
                }
            }
        } catch (InstantiationException e) {
            Logger.getInstance().error(e);
            throw new DaoLayerException(e);
        } catch (IllegalAccessException e) {
            Logger.getInstance().error(e);
            throw new DaoLayerException(e);
        }
        return result;
    }
}
