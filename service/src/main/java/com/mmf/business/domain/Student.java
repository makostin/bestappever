package com.mmf.business.domain;

/**
 * svetlana.voyteh
 * 13.03.13
 */
public class Student extends User{

    private static final long serialVersionUID = 8086956000068186141L;

    private Group group;
    private Integer yearOfEntrance;
    private Boolean isPraepostor;

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Integer getYearOfEntrance() {
        return yearOfEntrance;
    }

    public void setYearOfEntrance(Integer yearOfEntrance) {
        this.yearOfEntrance = yearOfEntrance;
    }

    public Boolean getPraepostor() {
        return isPraepostor;
    }

    public void setPraepostor(Boolean praepostor) {
        isPraepostor = praepostor;
    }
}
