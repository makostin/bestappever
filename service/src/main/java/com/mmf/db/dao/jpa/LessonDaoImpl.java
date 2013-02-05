package com.mmf.db.dao.jpa;

import com.mmf.business.domain.Lesson;
import com.mmf.db.dao.LessonDao;

import javax.inject.Named;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author svetlana.voyteh
 * @date: 3/12/12
 */
@Named
public class LessonDaoImpl extends GenericJpaDao<Long, LessonEntity> implements LessonDao{

    private static final String SQL_GET_GROUP_LESSONS_FOR_WEEK = "SELECT * FROM subject,classroom,user LEFT JOIN lesson ON user.ID = lesson.ID_User WHERE (lesson.ID IN (SELECT ID_Lesson FROM grouppair WHERE ID_SubGroup IN (SELECT ID FROM subgroup WHERE number = ? AND year = ?)) AND (End_week >= ? AND (?-Start_week)%Period=0) AND subject.ID = lesson.ID_Subject AND classroom.ID = lesson.ID_Classroom);";
    private static final String SQL_GET_LECTURER_LESSONS_FOR_WEEK = "SELECT * FROM lesson LEFT JOIN user ON lesson.ID_User = user.ID LEFT JOIN grouppair ON lesson.ID = grouppair.ID_Lesson LEFT JOIN subgroup ON grouppair.ID_SubGroup = subgroup.ID  WHERE (user.Name = ? AND (End_week >= ? AND (?-Start_week)%Period=0)) ;";


    public List<Lesson> getGroupLessons(int course, String subgroup, int week) {
        List<Lesson> lessonList = new ArrayList<Lesson>();
        Lesson lesson;
        Connection conn = null;
        try {
            Class.forName(DRIVER).newInstance();
            conn = DriverManager.getConnection(URL + DB_NAME, getProperties());
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_GET_GROUP_LESSONS_FOR_WEEK);
            preparedStatement.setString(1, subgroup);
            preparedStatement.setInt(2, Calendar.getInstance().get(Calendar.YEAR)-course);
            preparedStatement.setInt(3, week);
            preparedStatement.setInt(4, week);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                lesson = new Lesson();
                lesson.setSubject(resultSet.getString("subject.Name"));
                lesson.setTime(resultSet.getString("time"));
                lesson.setClassroom(String.valueOf(resultSet.getInt("classroom.Number")));
                lesson.setLecturer(resultSet.getString("name"));
                lesson.setDay(resultSet.getInt("day"));
                lesson.setId(resultSet.getLong("lesson.ID"));
                lesson.setCourse(course);
                lesson.setSubGroup(subgroup);
                lessonList.add(lesson);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return lessonList;
    }


    public List<Lesson> getLecturerLessons(String lecturer, int week) {
        List<Lesson> lessonList = new ArrayList<Lesson>();
        Lesson lesson;
        Connection conn = null;
        try {
            Class.forName(DRIVER).newInstance();
            conn = DriverManager.getConnection(URL + DB_NAME, getProperties());

            PreparedStatement preparedStatement = conn.prepareStatement(SQL_GET_LECTURER_LESSONS_FOR_WEEK);
            preparedStatement.setString(1, lecturer);
            preparedStatement.setInt(2, week);
            preparedStatement.setInt(3, week);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                lesson = new Lesson();
                lesson.setSubject(resultSet.getString("Lesson.Subject"));
                lesson.setTime(resultSet.getString("Lesson.Time"));
                lesson.setClassroom(String.valueOf(resultSet.getInt("Lesson.Classroom")));
                lesson.setDay(resultSet.getInt("Lesson.Day"));
                lesson.setId(resultSet.getLong("Lesson.ID"));
                lesson.setSubGroup(resultSet.getString("SubGroup.Number"));
                lesson.setCourse(Calendar.getInstance().get(Calendar.YEAR) - resultSet.getInt("SubGroup.Year"));
                lesson.setLecturer(lecturer);
                lessonList.add(lesson);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return lessonList;
    }

    @Override
    protected Class<LessonEntity> getEntityClass() {
        return LessonEntity.class;
    }
}
