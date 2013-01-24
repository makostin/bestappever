package com.mmf.db;

import com.mmf.db.dao.LecturerOptionDao;
import com.mmf.db.dao.LessonDao;
import com.mmf.db.dao.StudentOptionDao;

/**
 * @author svetlana.voyteh
 * @date: 3/13/12
 */
public class DaoObjectsFactory {
    private static DaoObjectsFactory ourInstance;

    private LessonDao lessonDao;
    private StudentOptionDao studentOptionDao;
    private LecturerOptionDao lecturerOptionDao;

    public static DaoObjectsFactory getInstance() {
        if (ourInstance == null) {
            synchronized (DaoObjectsFactory.class) {
                if (ourInstance == null) {
                    ourInstance = new DaoObjectsFactory();
                }
            }
        }
        return ourInstance;
    }

    private DaoObjectsFactory() {
    }

    public StudentOptionDao getStudentOptionDao() {
        if (studentOptionDao == null) {
            synchronized (DaoObjectsFactory.class) {
                if (studentOptionDao == null) {
                    studentOptionDao = new StudentOptionDao();
                }
            }
        }
        return studentOptionDao;
    }

    public LecturerOptionDao getLecturerOptionDao() {
        if (lecturerOptionDao == null) {
            synchronized (DaoObjectsFactory.class) {
                if (lecturerOptionDao == null) {
                    lecturerOptionDao = new LecturerOptionDao();
                }
            }
        }
        return lecturerOptionDao;
    }

    public LessonDao getLessonDao() {
        if (lessonDao == null) {
            synchronized (DaoObjectsFactory.class) {
                if (lessonDao == null) {
                    lessonDao = new LessonDao();
                }
            }
        }
        return lessonDao;
    }

}
