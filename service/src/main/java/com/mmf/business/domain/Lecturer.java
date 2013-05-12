package com.mmf.business.domain;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * svetlana.voyteh
 * 05.03.13
 */
@XmlRootElement
public class Lecturer extends User{

    private static final long serialVersionUID = 8115608997421822490L;
    private Department department;

    public Lecturer(){}

    public Lecturer(Long id) {
        super(id);
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
