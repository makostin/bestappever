package com.mmf.db.model;

import javax.persistence.*;

/**
 * svetlana.voyteh
 * 05.02.13
 */
@Entity
@Table(name = "study")
public class StudyEntity implements EntityClass<Long> {

    private static final long serialVersionUID = -847204836769092529L;
    private Long id;
    private GroupEntity group;
    private CurriculumEntity curriculum;
    private LecturerEntity lecturer;

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
    @JoinColumn(name = "IdGroup")
    public GroupEntity getGroup() {
        return group;
    }

    public void setGroup(GroupEntity group) {
        this.group = group;
    }

    @ManyToOne
    @JoinColumn(name = "IdCurriculum")
    public CurriculumEntity getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(CurriculumEntity curriculum) {
        this.curriculum = curriculum;
    }

    @ManyToOne
    @JoinColumn(name = "IdLecturer")
    public LecturerEntity getLecturer() {
        return lecturer;
    }

    public void setLecturer(LecturerEntity lecturer) {
        this.lecturer = lecturer;
    }
}
