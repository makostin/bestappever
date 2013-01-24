package com.mmf.service;

import com.mmf.db.DaoLayerException;
import com.mmf.db.DaoObjectsFactory;
import com.mmf.db.dao.LecturerOptionDao;
import com.mmf.db.dao.LessonDao;
import com.mmf.db.dao.StudentOptionDao;
import com.mmf.db.model.LecturerOption;
import com.mmf.db.model.Lesson;
import com.mmf.db.model.StudentOption;
import com.mmf.soap.RequestSoap;
import com.mmf.soap.RequestSoapException;
import com.mmf.soap.object.LessonSoap;
import com.mmf.util.Logger;
import org.ksoap2.serialization.SoapObject;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.List;

/**
 * @author svetlana.voyteh
 * @date: 2/3/12
 */
public class LessonService {

    private static final String SOAP_ACTION = "";
    private static final String METHOD_GET_GROUP_LESSONS = "getGroupLessons";
    private static final String METHOD_GET_LECTURER_LESSONS = "getLecturerLessons";
    private static final String NAMESPACE = "http://lessons";
    //    private static final String URL = "http://213.184.245.197:2081/service/lessons?wsdl";
    private static final String URL = "http://192.168.1.99:8080/service/lessons?wsdl";

    private final LessonDao lessonDao = DaoObjectsFactory.getInstance().getLessonDao();

    public LessonService() {
    }

//    public void deleteAll() throws BusinessLayerException {
//        try {
//            lessonDao.deleteAll();
//        } catch (DaoLayerException dle) {
//            Logger.getInstance().error(dle);
//            throw new BusinessLayerException(dle);
//        }
//    }
    
    public List<Lesson> getLessonsForDay(int course, String subgroup, int day) throws BusinessLayerException {
        try {
            return lessonDao.getLessonsForDay(course, subgroup, day);
        } catch (DaoLayerException dle) {
            Logger.getInstance().error(dle);
            throw new BusinessLayerException(dle);
        }
    }

    public List<Lesson> getLessonsForDay(String lecturer, int currentDay) throws BusinessLayerException {
        try {
            return lessonDao.getLessonsForDay(lecturer, currentDay);
        } catch (DaoLayerException dle) {
            Logger.getInstance().error(dle);
            throw new BusinessLayerException(dle);
        }
    }

    public boolean isLessonsExist(int course, String subgroup) throws BusinessLayerException {
        try {
            return lessonDao.isLessonsExist(course, subgroup);
        } catch (DaoLayerException dle) {
            Logger.getInstance().error(dle);
            throw new BusinessLayerException(dle);
        }
    }

    public boolean isLessonsExist(String lecturer) throws BusinessLayerException {
        try {
            return lessonDao.isLessonsExist(lecturer);
        } catch (DaoLayerException dle) {
            Logger.getInstance().error(dle);
            throw new BusinessLayerException(dle);
        }
    }

    public void insertLessons(int course, String subgroup, int week) throws BusinessLayerException {
        try {
            RequestSoap requestSoap = new RequestSoap(NAMESPACE, METHOD_GET_GROUP_LESSONS, URL, SOAP_ACTION);
            requestSoap.request(new String[]{"course", "subGroup", "week"}, new Object[]{course, subgroup, week});
            for (int i = 0; i < requestSoap.getResultCount(); i++) {
                LessonSoap lessonSoap = new LessonSoap(requestSoap.getResult(i));
                lessonDao.insert(lessonSoap.getLesson());
            }
            setStudentOptionDownload(true);

        } catch (XmlPullParserException e) {
            Logger.getInstance().error(e);
            throw new BusinessLayerException(e);
        } catch (IOException e) {
            Logger.getInstance().error(e);
            throw new BusinessLayerException(e);
        } catch (RequestSoapException rse) {
            Logger.getInstance().error(rse);
            throw new BusinessLayerException(rse);
        } catch (DaoLayerException dle) {
            Logger.getInstance().error(dle);
            throw new BusinessLayerException(dle);
        }
    }

    public void insertLessons(String lecturer, int currentWeek) throws BusinessLayerException {
        try {
            RequestSoap requestSoap = new RequestSoap(NAMESPACE, METHOD_GET_LECTURER_LESSONS, URL, SOAP_ACTION);
            requestSoap.request(new String[]{"lecturer", "week"}, new Object[]{lecturer, currentWeek});
            for (int i = 0; i < requestSoap.getResultCount(); i++) {
                LessonSoap lessonSoap = new LessonSoap(requestSoap.getResult(i));
                lessonDao.insert(lessonSoap.getLesson());
            }
            setLecturerOptionDownload(true);

        } catch (XmlPullParserException e) {
            Logger.getInstance().error(e);
            throw new BusinessLayerException(e);
        } catch (IOException e) {
            Logger.getInstance().error(e);
            throw new BusinessLayerException(e);
        } catch (DaoLayerException dle) {
            Logger.getInstance().error(dle);
            throw new BusinessLayerException(dle);
        } catch (RequestSoapException e) {
            Logger.getInstance().error(e);
            throw new BusinessLayerException(e);
        }
    }

    public void deleteCurrentLessons(int course, String group) throws BusinessLayerException {
        try {
            lessonDao.deleteLessons(course, group);
            setStudentOptionDownload(false);
        } catch (DaoLayerException dle) {
            Logger.getInstance().error(dle);
            throw new BusinessLayerException(dle);
        }
    }

    public void deleteCurrentLessons(String lecturer) throws BusinessLayerException {
        try {
            lessonDao.deleteLessons(lecturer);
            setLecturerOptionDownload(false);
        } catch (DaoLayerException dle) {
            Logger.getInstance().error(dle);
            throw new BusinessLayerException(dle);
        }
    }

    private void setStudentOptionDownload(boolean value) throws DaoLayerException {
        StudentOptionDao studentOptionDao = DaoObjectsFactory.getInstance().getStudentOptionDao();
        StudentOption studentOption = studentOptionDao.getCurrent();
        studentOption.setDownload(value);
        studentOptionDao.update(studentOption);
    }

    private void setLecturerOptionDownload(boolean value) throws DaoLayerException {
        LecturerOptionDao lecturerOptionDao = DaoObjectsFactory.getInstance().getLecturerOptionDao();
        LecturerOption lecturerOption = lecturerOptionDao.getCurrent();
        lecturerOption.setDownload(value);
        lecturerOptionDao.update(lecturerOption);
    }

}
