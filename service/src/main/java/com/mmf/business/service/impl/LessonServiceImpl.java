package com.mmf.business.service.impl;

import com.mmf.business.domain.Lesson;
import com.mmf.business.domain.utils.LessonHelper;
import com.mmf.business.service.BusinessServiceException;
import com.mmf.business.service.LessonService;
import com.mmf.db.dao.LessonDao;
import com.mmf.db.dao.jpa.LessonDaoImpl;
import com.mmf.db.model.LessonEntity;

import javax.inject.Inject;
import javax.inject.Named;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

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
