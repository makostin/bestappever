package com.mmf.business.service;

import com.mmf.business.domain.Lesson;
import com.mmf.db.model.LessonEntity;

import javax.jws.WebService;
import java.util.List;

/**
 * @author svetlana.voyteh
 * @date: 1/24/12
 */
//@WebService
public interface LessonService extends CrudService<Long, LessonEntity>{

//    List<Lesson> getGroupLessons(int course, String group, int week);
//    List<Lesson> getLecturerLessons(String lecturer, int week);
}
