package com.mmf.business.domain;


/**
 * svetlana.voyteh
 * 05.02.13
 */
public class Study implements DomainClass<Long> {

    private static final long serialVersionUID = -2608155811927545223L;
    private Long id;
    private Group group;
    private Curriculum curriculum;
    private Lecturer lecturer;

    @Override
    public Long getId() {
        return id;
    }

    @Override
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
