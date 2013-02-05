package com.mmf.db.model;

import javax.persistence.*;

/**
 * svetlana.voyteh
 * 05.02.13
 */
@Entity
@Table(name = "lecturer")
public class LecturerEntity extends UserEntity{
    private static final long serialVersionUID = 7411713197870734148L;

    private DepartmentEntity department;

    @ManyToOne
    @JoinColumn(name = "IdDepartment")
    public DepartmentEntity getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentEntity department) {
        this.department = department;
    }
}
