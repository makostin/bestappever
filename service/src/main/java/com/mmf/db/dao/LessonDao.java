package com.mmf.db.dao;

import com.mmf.business.domain.Lesson;

import java.util.List;

/**
 * @author svetlana.voyteh
 * @date: 5/12/12
 */
public interface LessonDao extends GenericDao<Long, LessonEntity>{

    List<Lesson> getGroupLessons(int course, String subgroup, int week);
    List<Lesson> getLecturerLessons(String lecturer, int week);
}
