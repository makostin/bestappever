package com.mmf.db.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author svetlana.voyteh
 * @date: 1/24/12
 */
@Entity
@Table(name = "lesson")
public class LessonEntity implements EntityClass<Long>{

    private static final long serialVersionUID = 661657339646660187L;
    private Long id;
    private Integer day;
    private Integer startWeek;
    private Integer endWeek;
    private Integer period;
    private UserEntity lecturer;
    private DisciplineEntity discipline;
    private ClassTimeEntity classTime;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "ID_Discipline")
    public DisciplineEntity getDiscipline() {
        return discipline;
    }

    public void setDiscipline(DisciplineEntity discipline) {
        this.discipline = discipline;
    }

    @ManyToOne
    @JoinColumn(name = "ID_Time")
    public ClassTimeEntity getClassTime() {
        return classTime;
    }

    public void setClassTime(ClassTimeEntity classTime) {
        this.classTime = classTime;
    }

    @ManyToOne
    @JoinColumn(name = "ID_User")
    public UserEntity getLecturer() {
        return lecturer;
    }

    public void setLecturer(UserEntity lecturer) {
        this.lecturer = lecturer;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getStartWeek() {
        return startWeek;
    }

    public void setStartWeek(Integer startWeek) {
        this.startWeek = startWeek;
    }

    public Integer getEndWeek() {
        return endWeek;
    }

    public void setEndWeek(Integer endWeek) {
        this.endWeek = endWeek;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

//    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "lessons")
//    public Set<GroupEntity> getGroups() {
//        return groups;
//    }
//
//    public void setGroups(Set<GroupEntity> groups) {
//        this.groups = groups;
//    }

    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
                ", name='" + discipline.getName() + '\'' +
                ", time='" + classTime.getStart() + '\'' +
                ", lecturer='" + lecturer.getName() + '\'' +
                ", day=" + day +
                '}';
    }
}
