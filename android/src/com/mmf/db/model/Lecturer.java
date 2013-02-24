package com.mmf.db.model;

/**
 * svetlana.voyteh
 * 13.02.13
 */
public class Lecturer implements Entity{
    private static final long serialVersionUID = -7103450854982721729L;

    private Long id;
    private Long systemId;
    private Department department;
    private String fullName;

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Long getSystemId() {
        return systemId;
    }

    public void setSystemId(Long systemId) {
        this.systemId = systemId;
    }
}
