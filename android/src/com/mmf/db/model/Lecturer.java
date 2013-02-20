package com.mmf.db.model;

/**
 * svetlana.voyteh
 * 13.02.13
 */
public class Lecturer implements Entity{
    private static final long serialVersionUID = -7103450854982721729L;

    private Long id;
    private Long systemId;
    private Long departmentId;
    private String fullName;

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
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
