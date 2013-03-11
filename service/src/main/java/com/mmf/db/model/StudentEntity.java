package com.mmf.db.model;

import javax.persistence.*;

/**
 * svetlana.voyteh
 * 05.02.13
 */
@Entity
@Table(name = "student")
public class StudentEntity extends UserEntity{
    private static final long serialVersionUID = 7853939168145932279L;

    private GroupEntity group;
    private Integer yearOfEntrance;
    private Boolean isPraepostor;

    @ManyToOne
    @JoinColumn(name = "IdGroup")
    public GroupEntity getGroup() {
        return group;
    }

    public void setGroup(GroupEntity group) {
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
