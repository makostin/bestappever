package com.mmf.soap;

import com.mmf.business.domain.Lesson;

import javax.jws.WebService;
import java.util.List;

/**
 * @author svetlana.voyteh
 * @date: 5/12/12
 */
@WebService
public interface SoapLessonService {

    List<Lesson> getGroupLessons(int course, String group, int week);
    List<Lesson> getLecturerLessons(String lecturer, int week);
}
