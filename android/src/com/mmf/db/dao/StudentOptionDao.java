package com.mmf.db.dao;

import com.mmf.db.DaoLayerException;
import com.mmf.db.EntityManager;
import com.mmf.db.TransactionException;
import com.mmf.db.model.StudentOption;
import com.mmf.util.Logger;
import com.mmf.util.StringUtils;

import java.util.List;

/**
 * @author svetlana.voyteh
 * @date: 2/12/12
 */
public class StudentOptionDao {

    private final EntityManager entityManager = EntityManager.getInstance();

    public StudentOptionDao() {
    }

    public StudentOption getCurrent() throws DaoLayerException {
        try {
            String selection = "(is_current = 1)";
            return entityManager.get(StudentOption.class, selection, null);
        } catch (InstantiationException e) {
            Logger.getInstance().error(e);
            throw new DaoLayerException(e);
        } catch (IllegalAccessException e) {
            Logger.getInstance().error(e);
            throw new DaoLayerException(e);
        }
    }

    public StudentOption get(int course, String group) throws DaoLayerException {
        try {
            String selection = StringUtils.getSelectionString(new String[]{StudentOption.COLUMN_COURSE, StudentOption.COLUMN_SUBGROUP});
            String[] selectionArgs = new String[]{String.valueOf(course), group};
            return entityManager.get(StudentOption.class, selection, selectionArgs);
        } catch (InstantiationException e) {
            Logger.getInstance().error(e);
            throw new DaoLayerException(e);
        } catch (IllegalAccessException e) {
            Logger.getInstance().error(e);
            throw new DaoLayerException(e);
        }
    }

    public void update(StudentOption studentOption) throws DaoLayerException {
        try {
            entityManager.beginTransaction();
            entityManager.update(studentOption);
            entityManager.commitTransaction();
        } catch (TransactionException e){
            entityManager.rollbackTransaction();
            Logger.getInstance().error(e);
            throw new DaoLayerException(e);
        }
    }

    public boolean isLessonsUpdate(int currentWeek) throws DaoLayerException {
        boolean result = false;
        try {
            String selection = "(is_current = 1)";
            StudentOption studentOption = entityManager.get(StudentOption.class, selection, null);
            if (studentOption != null) {
                if(studentOption.getWeek() < currentWeek || !studentOption.isDownload()){
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


//    public List<StudentOption> getDownloaded() throws DaoLayerException {
//        try {
//            String selection = "(is_download = 1)";
//            return entityManager.find(StudentOption.class,selection, null, null, null);
//        } catch (InstantiationException e) {
//            Logger.getInstance().error(e);
//            throw new DaoLayerException(e);
//        } catch (IllegalAccessException e) {
//            Logger.getInstance().error(e);
//            throw new DaoLayerException(e);
//        }
//    }

    public boolean isLogin() throws DaoLayerException {
        return getLogin() != null;
    }

    public StudentOption getLogin() throws DaoLayerException {
        try {
            String selection = "(is_login = 1)";
            return entityManager.get(StudentOption.class, selection, null);
        } catch (InstantiationException e) {
            Logger.getInstance().error(e);
            throw new DaoLayerException(e);
        } catch (IllegalAccessException e) {
            Logger.getInstance().error(e);
            throw new DaoLayerException(e);
        }
    }

}
