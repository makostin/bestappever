package com.mmf.soap.impl;

import com.mmf.business.domain.Lesson;
import com.mmf.db.dao.LessonDao;
import com.mmf.db.dao.jpa.LessonDaoImpl;
import com.mmf.soap.SoapLessonService;

import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

/**
 * @author svetlana.voyteh
 * @date: 5/12/12
 */
@WebService(targetNamespace = "http://lessons")
public class SoapLessonServiceImpl implements SoapLessonService{

    private LessonDao dao;

    public SoapLessonServiceImpl() {
        this.dao = new LessonDaoImpl();
    }

    public List<Lesson> getGroupLessons(@WebParam(name = "course") int course,
                                        @WebParam(name = "subGroup") String subgroup,
                                        @WebParam(name = "week") int week) {
        return dao.getGroupLessons(course, subgroup, week);
    }

    public List<Lesson> getLecturerLessons(@WebParam(name = "lecturer")String lecturer,
                                           @WebParam(name = "week") int week) {
        return dao.getLecturerLessons(lecturer, week);
    }
}
