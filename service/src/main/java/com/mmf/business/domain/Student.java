package com.mmf.business.domain;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * svetlana.voyteh
 * 13.03.13
 */
@XmlRootElement
public class Student extends User{

    private static final long serialVersionUID = 8086956000068186141L;

    private Group group;
    private Long groupId;
    private Integer yearOfEntrance;
    private Boolean isPraepostor;

    @XmlTransient
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

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
}
