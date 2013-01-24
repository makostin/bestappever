package com.mmf.soap.object;

import com.mmf.db.model.Lesson;
import org.ksoap2.serialization.SoapObject;

/**
 * @author svetlana.voyteh
 * @date: 4/9/12
 */
public class LessonSoap extends BaseSoap{


    public LessonSoap(SoapObject lessonObject) {
        super(lessonObject);
    }

    public Lesson getLesson(){
        Lesson lesson = new Lesson();
        lesson.setClassRoom(getString(Lesson.COLUMN_CLASSROOM));
        lesson.setSubject(getString(Lesson.COLUMN_SUBJECT));
        lesson.setLecturer(getString(Lesson.COLUMN_LECTURER));
        lesson.setTime(getString(Lesson.COLUMN_TIME));
        lesson.setSubgroup(getString(Lesson.COLUMN_SUBGROUP));
        lesson.setDay(getInt(Lesson.COLUMN_DAY));
        lesson.setCourse(getInt(Lesson.COLUMN_COURSE));
        lesson.setLessonId(getLong(ID));
        return lesson;
    }
    

}
