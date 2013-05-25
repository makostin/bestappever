package com.mmf.rest.response.study;

import com.mmf.business.domain.Curriculum;
import com.mmf.business.domain.Group;
import com.mmf.business.domain.Lecturer;
import com.mmf.business.domain.Study;

/**
 * User: svetlana.voyteh
 * Date: 25.05.13
 */
public class StudyResponse {

    private Long id;
    private Group group;
    private Curriculum curriculum;
    private Lecturer lecturer;

    public StudyResponse() {
    }

    public StudyResponse(Study study) {
        this.id = study.getId();
        this.curriculum = study.getCurriculum();
        this.group = study.getGroup();
        this.lecturer = study.getLecturer();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Curriculum getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(Curriculum curriculum) {
        this.curriculum = curriculum;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

}
