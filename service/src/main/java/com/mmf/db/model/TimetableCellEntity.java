package com.mmf.db.model;

import javax.persistence.*;

/**
 * User: admin
 * Date: 14.10.12
 */
@Entity
@Table(name = "timetableCell")
public class TimetableCellEntity implements EntityClass<Long>{
    private static final long serialVersionUID = -7485889068565062693L;
    private Long id;
    private Integer semester;
    private Integer yearOfEntrance;
    private LessonEntity lesson;
    private ClassroomEntity classroom;
    private GroupEntity group;

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

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public Integer getYearOfEntrance() {
        return yearOfEntrance;
    }

    public void setYearOfEntrance(Integer yearOfEntrance) {
        this.yearOfEntrance = yearOfEntrance;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_Lesson")
    public LessonEntity getLesson() {
        return lesson;
    }

    public void setLesson(LessonEntity lesson) {
        this.lesson = lesson;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_Classroom")
    public ClassroomEntity getClassroom() {
        return classroom;
    }

    public void setClassroom(ClassroomEntity classroom) {
        this.classroom = classroom;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_Group")
    public GroupEntity getGroup() {
        return group;
    }

    public void setGroup(GroupEntity group) {
        this.group = group;
    }
}
