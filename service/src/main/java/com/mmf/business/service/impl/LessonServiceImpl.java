package com.mmf.business.service.impl;

import com.mmf.business.service.LessonService;
import com.mmf.db.dao.LessonDao;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author svetlana.voyteh
 * @date: 1/24/12
 */
//@WebService(targetNamespace = "http://lessons")
@Named
public class LessonServiceImpl extends AbstractCrudService<Long, LessonEntity, LessonDao> implements LessonService {

    @Inject
    private LessonDao dao;


//    @Override
//    public List<Lesson> getGroupLessons(@WebParam(name = "course") int course,
//                                        @WebParam(name = "subGroup") String subgroup,
//                                        @WebParam(name = "week") int week) {
//        return dao.getGroupLessons(course, subgroup, week);
//    }
//
//    @Override
//    public List<Lesson> getLecturerLessons(@WebParam(name = "lecturer")String lecturer,
//                                           @WebParam(name = "week") int week) {
//        return dao.getLecturerLessons(lecturer, week);
//    }


    @Override
    protected LessonDao getDao() {
        return dao;
    }

}
